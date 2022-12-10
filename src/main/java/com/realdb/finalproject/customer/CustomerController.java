package com.realdb.finalproject.customer;

import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
import com.realdb.finalproject.utility.JWTProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

import static com.realdb.finalproject.security.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @GetMapping(path ="all")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping()
    public Optional<Customer> findCustomerByUsername(@RequestParam String username) {
        return customerService.findCustomerByUsername(username);
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
