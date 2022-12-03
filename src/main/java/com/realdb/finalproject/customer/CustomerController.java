package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path ="all")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping()
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("customerId={customerId}")
    public Customer updateCustomer(@PathVariable(value = "customerId") Integer id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("customerId={customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") Integer id) {
        customerService.deleteCustomer(id);
    }
}
