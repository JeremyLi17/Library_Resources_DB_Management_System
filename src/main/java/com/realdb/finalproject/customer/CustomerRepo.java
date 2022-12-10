package com.realdb.finalproject.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByUsername(String username);
}
