package com.realdb.finalproject.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/10
 */
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByUsername(String username);
    Optional<Employee> findEmployeeByEmail(String email);
}
