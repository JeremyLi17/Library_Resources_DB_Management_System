package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> { }
