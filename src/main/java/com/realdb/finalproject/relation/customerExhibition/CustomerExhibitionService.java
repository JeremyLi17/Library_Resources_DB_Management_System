package com.realdb.finalproject.relation.customerExhibition;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.customer.CustomerRepo;
import com.realdb.finalproject.entity.event.Exhibition;
import com.realdb.finalproject.entity.event.ExhibitionRepo;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.ExhibitionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class CustomerExhibitionService {
    private final CustomerExhibitionRepo customerExhibitionRepo;
    private final CustomerRepo customerRepo;
    private final ExhibitionRepo exhibitionRepo;

    @Autowired
    public CustomerExhibitionService(CustomerExhibitionRepo customerExhibitionRepo,
                                     CustomerRepo customerRepo,
                                     ExhibitionRepo exhibitionRepo) {
        this.customerExhibitionRepo = customerExhibitionRepo;
        this.customerRepo = customerRepo;
        this.exhibitionRepo = exhibitionRepo;
    }

    public int getNextRegistrationId() {
        return (int)customerExhibitionRepo.count() + 1;
    }

    public List<Exhibition> getExhibitionByCustomer(String username) throws CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepo.findCustomerByUsername(username);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer: " + username + " not found");
        }
        Customer customer = customerOpt.get();
        List<Exhibition> exhibitions = customerExhibitionRepo.getByCustomer(customer.getId());
        return exhibitions;
    }

    public List<Customer> getCustomerToExhibition(Integer exhibitionId)
            throws ExhibitionNotFoundException {
        Optional<Exhibition> exhibitionOpt = exhibitionRepo.findById(exhibitionId);
        if (exhibitionOpt.isEmpty()) {
            throw new ExhibitionNotFoundException("Exhibition: " + exhibitionId + " not found");
        }
        List<Customer> customers = customerExhibitionRepo.getByExhibition(exhibitionId);
        return customers;
    }


    public CustomerExhibition addCustomerExhibition(String username, Integer id)
            throws CustomerNotFoundException, ExhibitionNotFoundException {
        Optional<Customer> customerOpt = customerRepo.findCustomerByUsername(username);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer: " + username + " not found");
        }
        Optional<Exhibition> exhibitionOpt = exhibitionRepo.findById(id);
        if (exhibitionOpt.isEmpty()) {
            throw new ExhibitionNotFoundException("Exhibition: " + id + " not found");
        }
        CustomerExhibition customerExhibition = new CustomerExhibition();
        customerExhibition.setCustomer(customerOpt.get());
        customerExhibition.setExhibitionEvent(exhibitionOpt.get());

        // set id
        CustomerExhibitionId customerExhibitionId = new CustomerExhibitionId();
        customerExhibition.setId(customerExhibitionId);
        customerExhibitionId.setCustomerId(customerOpt.get().getId());
        customerExhibitionId.setRegistrationId(getNextRegistrationId());
        customerExhibitionRepo.save(customerExhibition);
        return customerExhibition;
    }

    public void deleteCustomerExhibition(CustomerExhibitionId customerExhibitionId) {
        customerExhibitionRepo.deleteById(customerExhibitionId);
    }
}
