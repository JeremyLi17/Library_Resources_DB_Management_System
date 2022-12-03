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
    public Customer updateCustomer(Integer customerId, String cEmail, String cFName, String cLName, String cMName,
                                   Long cPhoneNo, String idType, String idNo) {

        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new IllegalStateException(
                "Customer with id " + customerId + " does not exist"
        ));

        if (cEmail != null && cEmail.length() > 0 && !cEmail.equals(customer.getCEmail())) {

            Optional<Customer> customerOptional = customerRepo.findCustomerByCEmail(cEmail);

            if (customerOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            customer.setCEmail(cEmail);
        }
        if (cFName != null && cFName.length() > 0 && !cFName.equals(customer.getCFname())) {
            customer.setCFname(cFName);
        }
        if (cLName != null && cLName.length() > 0 && !cLName.equals(customer.getCLname())) {
            customer.setCLname(cLName);
        }
        if (cMName != null && cMName.length() > 0 && !cMName.equals(customer.getCMname())) {
            customer.setCMname(cMName);
        }
        if (cPhoneNo != null && !cPhoneNo.equals(customer.getCPhoneno())) {
            customer.setCPhoneno(cPhoneNo);
        }
        if (idType != null && idType.length() > 0 && !idType.equals(customer.getIdType())) {
            customer.setIdType(idType);
        }
        if (idNo != null && idNo.length() > 0 && !idNo.equals(customer.getIdNo())) {
            customer.setIdNo(idNo);
        }

        return customerRepo.save(customer);
    }
}
