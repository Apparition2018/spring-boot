package com.ljh.loader;

import com.ljh.entity.primary.Employee;
import com.ljh.repository.primary.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * DataLoader
 *
 * @author ljh
 * created on 2021/8/31 12:53
 */
@Configuration
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new Employee("Frodo Baggins", "ring bearer"));
        employeeRepository.save(new Employee("Bilbo Baggins", "burglar"));
    }
}
