package com.realdb.finalproject.customer;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
import com.realdb.finalproject.utility.EmailService;
import com.realdb.finalproject.utility.JWTProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.List;

import static com.realdb.finalproject.security.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    public static final String CUSTOMER_DELETED_SUCCESSFULLY = "Customer deleted successfully";

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final EmailService emailService;

    @Autowired
    public CustomerController(CustomerService customerService,
                              @Qualifier("authManagerForCustomer")
                                      AuthenticationManager authenticationManager,
                              JWTProvider jwtProvider,
                              EmailService emailService) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.emailService = emailService;
    }

    @GetMapping(path ="/list")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Customer> findCustomerByUsername(@PathVariable("username") String username)
            throws UserNotFoundException {
        Optional<Customer> customerByUsername = customerService.findCustomerByUsername(username);
        if (customerByUsername.isEmpty()) {
            throw new UserNotFoundException("User: " + username + " not found");
        }
        return new ResponseEntity<>(customerByUsername.get(), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        Customer registerCustomer = customerService.registerCustomer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNo(),
                customer.getIdType(),
                customer.getIdNo(),
                customer.getUsername(),
                customer.getPassword());

        UserPrincipal userPrincipal = new UserPrincipal(registerCustomer);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(registerCustomer, jwtHeader, CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer) {

        authenticate(customer.getUsername(),customer.getPassword());
        Customer loginCustomer = customerService
                .findCustomerByUsername(customer.getUsername()).get();
        UserPrincipal userPrincipal = new UserPrincipal(loginCustomer);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginCustomer, jwtHeader, OK);
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<Customer> resetPassword(@RequestBody Customer customer) throws MessagingException {
        Customer updateCustomer = customerService.resetPassword(customer.getUsername(),
                customer.getPassword());
//        emailService.sendNewPasswordEmail(updateCustomer.getUsername(), updateCustomer.getPassword(), updateCustomer.getEmail());
        return new ResponseEntity<>(updateCustomer, ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(
            @RequestParam("currentUsername") String currentUsername,
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false) String newFirstName,
            @RequestParam(required = false) String newLastName,
            @RequestParam(required = false) String newMiddleName,
            @RequestParam(required = false) String newPhoneNo,
            @RequestParam(required = false) String newIdType,
            @RequestParam(required = false) String newIdNo)
            throws UserNotFoundException, EmailExistException, UsernameExistException {

        Customer updateCustomer = customerService.updateCustomer(currentUsername, newUsername, newEmail,
                newFirstName, newLastName, newMiddleName, newPhoneNo, newIdType, newIdNo);
        return new ResponseEntity<>(updateCustomer, OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteCustomerById(@RequestParam("id") Integer id) {
        customerService.deleteCustomer(id);
        return response(NO_CONTENT, CUSTOMER_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus status, String message) {
        return new ResponseEntity<>(new HttpResponse(status.value(), status,
                status.getReasonPhrase().toUpperCase(), message.toUpperCase()), status);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }
}
