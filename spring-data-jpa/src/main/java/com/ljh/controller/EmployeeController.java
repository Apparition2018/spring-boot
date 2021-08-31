package com.ljh.controller;

import com.ljh.entity.primary.Employee;
import com.ljh.repository.primary.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author ljh
 * created on 2021/8/31 14:06
 */
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        List<EntityModel<Employee>> employees = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(employees,
                        linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee employee) {

        try {
            Employee savedEmployee = repository.save(employee);

            EntityModel<Employee> employeeResource = EntityModel.of(savedEmployee,
                    linkTo(methodOn(EmployeeController.class).findOne(savedEmployee.getId())).withSelfRel());

            return ResponseEntity
                    .created(new URI(employeeResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(employeeResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create " + employee);
        }
    }

    @GetMapping("/employees/{id}")
    ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {

        return repository.findById(id)
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

        employee.setId(id);
        repository.save(employee);

        Link newlyCreatedLink = linkTo(methodOn(EmployeeController.class).findOne(id)).withSelfRel();

        try {
            return ResponseEntity.noContent().location(new URI(newlyCreatedLink.getHref())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to update " + employee);
        }
    }
}
