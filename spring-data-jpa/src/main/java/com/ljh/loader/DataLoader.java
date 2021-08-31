package com.ljh.loader;

import com.ljh.entity.primary.Employee;
import com.ljh.repository.primary.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * DataLoader
 *
 * @author ljh
 * created on 2021/8/31 12:53
 */
@Component
public class DataLoader {

    @Bean
    CommandLineRunner init(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Frodo Baggins", "ring bearer"));
            repository.save(new Employee("Bilbo Baggins", "burglar"));
        };
    }
}
