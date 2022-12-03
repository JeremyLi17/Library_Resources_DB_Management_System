package com.realdb.finalproject.customer;

import com.realdb.finalproject.entity.Author;
import com.realdb.finalproject.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomer(Integer customerId) {
        return customerRepo.findById(customerId);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Integer customerId, String email, String cFName, String cLName, String cMName,
                                   Long cPhoneNo, String idType, String idNo) {

        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new IllegalStateException(
                "Customer with id " + customerId + " does not exist"
        ));

        //TODO: NEED TO MODIFY after C_EMAIL's datatype is changed to VARCHAR(30)
        if (email != null && email.length() > 0 && (email.endsWith(".edu") || email.endsWith(".com"))) {

            email += " ".repeat(customer.getCEmail().length() - email.length());

            if (!email.equals(customer.getCEmail())) {

                Optional<Customer> studentOptional = customerRepo.findCustomerBycEmail(email);

                if (studentOptional.isPresent()) {
                    throw new IllegalStateException("email taken");
                }

                customer.setCEmail(email);
            }
        }

        if (cFName != null && cFName.length() > 0 && !cFName.equals(customer.getCFname())) {
            customer.setCFname(cFName);
        }

        if (cLName != null && cLName.length() > 0) {

            cLName += " ".repeat(customer.getCLname().length() - cLName.length());

            if (!cLName.equals(customer.getCLname())) {
                customer.setCLname(cLName);
            }
        }

        if (cMName != null && cMName.length() > 0 && !cMName.equals(customer.getCMname())) {
            customer.setCMname(cMName);
        }

        //TODO: NEED TO MODIFY after cPhoneNo's datatype is changed to CHAR(10)
        if (cPhoneNo != null && !cPhoneNo.equals(customer.getCPhoneno())) {
            customer.setCPhoneno(cPhoneNo);
        }

        if (idType != null && idType.length() > 0 && !idType.equals(customer.getIdType())) {
            customer.setIdType(idType);
        }

        if (idNo != null && idNo.length() > 0 && !idNo.equals(customer.getIdNo())) {
            customer.setIdNo(idNo);
        }
    }
}
