package com.realdb.finalproject.customer;

import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
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
    public Customer createCustomer(@RequestBody Customer customer)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        return customerService.registerCustomer(
                customer.getFName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNo(),
                customer.getIdType(),
                customer.getIdNo(),
                customer.getUsername(),
                customer.getPassword());
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable("id") Integer id,
                                   @RequestParam(required = false) String cEmail,
                                   @RequestParam(required = false) String cFName,
                                   @RequestParam(required = false) String cLName,
                                   @RequestParam(required = false) String cMName,
                                   @RequestParam(required = false) String cPhoneNo,
                                   @RequestParam(required = false) String idType,
                                   @RequestParam(required = false) String idNo) {

        customerService.updateCustomer(id, cEmail, cFName, cLName, cMName, cPhoneNo, idType, idNo);
    }

    @DeleteMapping()
    public void deleteCustomer(@RequestParam Integer id) {
        customerService.deleteCustomer(id);
    }
}
