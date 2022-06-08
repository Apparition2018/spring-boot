package com.ljh.controller;

import com.ljh.dto.AppResponse;
import com.ljh.dto.TaskCompletionRequest;
import com.ljh.dto.UserRegistrationRequest;
import com.ljh.exception.UserRegistrationTaskException;
import com.ljh.service.UserRegistrationService;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.task.model.Task;
import org.activiti.engine.impl.identity.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserRegistrationController
 *
 * @author ljh
 * created on 2022/6/9 2:03
 */
@RestController
@RequestMapping(value = "/process/secure/user-registration")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> startRegistrationProcess(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        ProcessInstance processInstance = userRegistrationService.startRegistrationProcess(userRegistrationRequest);

        AppResponse apiResponse = new AppResponse();
        apiResponse.setData(processInstance);
        apiResponse.setMessage("Registration process started");
        apiResponse.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> getTaskDefinitions(Authentication authentication) {
        List<Task> taskList = userRegistrationService.getApprovalTasks();

        AppResponse apiResponse = new AppResponse();
        apiResponse.setData(taskList);
        apiResponse.setMessage("Retrieved task list");
        apiResponse.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/tasks/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse> completeTask(@PathVariable String taskId, @RequestBody TaskCompletionRequest taskRequest) {
        AppResponse apiResponse = new AppResponse();
        try {
            Task task = userRegistrationService.completeTask(taskId, taskRequest);
            apiResponse.setData(task);
            apiResponse.setMessage("Task completed successfully");
            apiResponse.setStatus(HttpStatus.OK);
        } catch (UserRegistrationTaskException ex) {
            apiResponse.setMessage(ex.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(apiResponse);
    }

}
