package com.ljh.repository.primary;

import com.ljh.entity.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * EmployeeRepository
 *
 * @author ljh
 * created on 2021/8/31 12:55
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
