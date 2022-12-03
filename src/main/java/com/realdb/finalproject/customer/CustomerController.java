package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @GetMapping()
    public Optional<Customer> getCustomer(@RequestParam Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping()
    public Customer updateCustomer(@RequestParam Integer id,
                                   @RequestParam(required = false) String cEmail,
                                   @RequestParam(required = false) String cFName,
                                   @RequestParam(required = false) String cLName,
                                   @RequestParam(required = false) String cMName,
                                   @RequestParam(required = false) Long cPhoneNo,
                                   @RequestParam(required = false) String idType,
                                   @RequestParam(required = false) String idNo) {

        return customerService.updateCustomer(id, cEmail, cFName, cLName, cMName, cPhoneNo, idType, idNo);
    }

    @DeleteMapping()
    public void deleteCustomer(@RequestParam Integer id) {
        customerService.deleteCustomer(id);
    }
}
