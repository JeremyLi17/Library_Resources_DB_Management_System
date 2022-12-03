package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    public Customer updateCustomer(Integer customerId, Customer customerDetails) {
        if (customerRepo.findById(customerId).isEmpty()) {
            return null;
        }

        Customer customer = customerRepo.findById(customerId).get();

        if (customerDetails.getCEmail() != null && !customerDetails.getCEmail().equals(customer.getCEmail())) {
            customer.setCEmail(customerDetails.getCEmail());
        }
        if (customerDetails.getCFname() != null && !customerDetails.getCFname().equals(customer.getCFname())) {
            customer.setCFname(customerDetails.getCFname());
        }
        if (customerDetails.getCLname() != null && !customerDetails.getCLname().equals(customer.getCLname())) {
            customer.setCLname(customerDetails.getCLname());
        }
        if (customerDetails.getCMname() != null && !customerDetails.getCMname().equals(customer.getCMname())) {
            customer.setCMname(customerDetails.getCMname());
        }
        if (customerDetails.getCPhoneno() != null && !customerDetails.getCPhoneno().equals(customer.getCPhoneno())) {
            customer.setCPhoneno(customerDetails.getCPhoneno());
        }
        if (customerDetails.getIdType() != null && !customerDetails.getIdType().equals(customer.getIdType())) {
            customer.setIdType(customerDetails.getIdType());
        }
        if(customerDetails.getIdNo() != null && !customerDetails.getIdNo().equals(customer.getIdNo())) {
            customer.setIdNo(customerDetails.getIdNo());
        }

        return customerRepo.save(customer);
    }
}
