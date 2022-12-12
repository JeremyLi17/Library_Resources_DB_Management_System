package com.realdb.finalproject.relation.customerExhibition;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.event.Exhibition;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.ExhibitionNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/customerExhibition")
public class CustomerExhibitionController {

    public static final String CUSTOMER_EXHIBITION_DELETED_SUCCESSFULLY = "Customer Exhibition deleted successfully";
    private final CustomerExhibitionService customerExhibitionService;

    @Autowired
    public CustomerExhibitionController(
            CustomerExhibitionService customerExhibitionService) {
        this.customerExhibitionService = customerExhibitionService;
    }

    @GetMapping("/list/exhibition/{username}")
    public ResponseEntity<List<Exhibition>> getExhibitionByCustomer(
            @PathVariable("username") String username) throws CustomerNotFoundException {
        List<Exhibition> exhibitions = customerExhibitionService.getExhibitionByCustomer(username);
        return new ResponseEntity<>(exhibitions, OK);
    }

    @GetMapping("/list/customer/{id}")
    public ResponseEntity<List<Customer>> getCustomerToExhibition(
            @PathVariable("id") Integer exhibitionId) throws ExhibitionNotFoundException {
        List<Customer> customers = customerExhibitionService.getCustomerToExhibition(exhibitionId);
        return new ResponseEntity<>(customers, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerExhibition> addCustomerExhibition(
            @RequestBody CustomerExhibition customerExhibition)
            throws CustomerNotFoundException, ExhibitionNotFoundException {
        customerExhibitionService.addCustomerExhibition(
                customerExhibition.getCustomer().getUsername(),
                customerExhibition.getExhibitionEvent().getId()
        );
        return new ResponseEntity<>(customerExhibition, CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteCustomerExhibition(
            @RequestParam("id") CustomerExhibitionId customerExhibitionId) {
        customerExhibitionService.deleteCustomerExhibition(customerExhibitionId);
        return BuildResponse.build(NO_CONTENT, CUSTOMER_EXHIBITION_DELETED_SUCCESSFULLY);
    }
}
