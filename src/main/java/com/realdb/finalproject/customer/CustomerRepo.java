package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerBycEmail(String email);
}
