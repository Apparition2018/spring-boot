package com.ljh.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.dto.TaskCompletionRequest;
import com.ljh.dto.UserRegistrationRequest;
import com.ljh.exception.UserRegistrationTaskException;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * UserRegistrationService
 *
 * @author ljh
 * created on 2022/6/9 1:39
 */
@Slf4j
@Service
public class UserRegistrationService {

    private static final String USER_REGISTRATION_PROCESS = "userRegistrationProcess";
    private static final String VAR_REGISTRATION_REQUEST = "registrationRequest";

    private final ProcessRuntime processRuntime;
    private final TaskRuntime taskRuntime;

    public UserRegistrationService(ProcessRuntime processRuntime, TaskRuntime taskRuntime) {
        this.processRuntime = processRuntime;
        this.taskRuntime = taskRuntime;
    }

    /**
     * 利用 ProcessRuntime 启动流程实例
     */
    public ProcessInstance startRegistrationProcess(UserRegistrationRequest userRegistrationRequest) {
        return processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey(USER_REGISTRATION_PROCESS)
                .withVariable(VAR_REGISTRATION_REQUEST, userRegistrationRequest)
                .build());
    }

    public List<Task> getApprovalTasks() {
        return taskRuntime.tasks(Pageable.of(0, 10)).getContent();
    }

    /**
     * 利用 TaskRuntime 完成任务
     */
    public Task completeTask(String taskId, TaskCompletionRequest taskCompletionRequest) {
        Task task;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<VariableInstance> variables = taskRuntime.variables(TaskPayloadBuilder.variables().withTaskId(taskId).build());

            JsonNode contentToProcessNode = Objects.requireNonNull(variables.stream().filter(var -> VAR_REGISTRATION_REQUEST.equals(var.getName())).findFirst().orElse(null)).getValue();
            UserRegistrationRequest contentToProcess = objectMapper.treeToValue(contentToProcessNode, UserRegistrationRequest.class);

            task = taskRuntime.complete(TaskPayloadBuilder.complete()
                    .withTaskId(taskId)
                    .withVariable(VAR_REGISTRATION_REQUEST, contentToProcess)
                    .withVariable("comment", taskCompletionRequest.getComment())
                    .withVariable("approved", taskCompletionRequest.isApproved()).build());
        } catch (Exception e) {
            throw new UserRegistrationTaskException(e.getMessage());
        }

        return task;
    }
}
