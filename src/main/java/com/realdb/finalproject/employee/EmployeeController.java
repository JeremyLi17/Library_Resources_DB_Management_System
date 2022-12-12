package com.realdb.finalproject.employee;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
import com.realdb.finalproject.utility.BuildResponse;
import com.realdb.finalproject.utility.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.realdb.finalproject.security.SecurityConstant.JWT_TOKEN_HEADER;
import static com.realdb.finalproject.utility.BuildResponse.build;
import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/10
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    public static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee deleted successfully";
    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              @Qualifier("authManagerForEmployee")
                                      AuthenticationManager authenticationManager,
                              JWTProvider jwtProvider) {
        this.employeeService = employeeService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping(path ="/list")
    public List<Employee> getCustomers() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/find/{username}")
    public Optional<Employee> findCustomerByUsername(@PathVariable("username") String username) {
        return employeeService.findEmployeeByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        Employee registerEmployee= employeeService.registerEmployee(
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword());
        UserPrincipal userPrincipal = new UserPrincipal(registerEmployee);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(registerEmployee, jwtHeader, CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {
        authenticate(employee.getUsername(), employee.getPassword());
        Employee loginEmployee = employeeService
                .findEmployeeByUsername(employee.getUsername()).get();
        UserPrincipal userPrincipal = new UserPrincipal(loginEmployee);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginEmployee, jwtHeader, OK);
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<Employee> resetPassword(@RequestBody Customer customer) {
        Employee updateCustomer = employeeService.resetPassword(customer.getUsername(),
                customer.getPassword());
        return new ResponseEntity<>(updateCustomer, ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateCustomer(
            @RequestParam String currentUsername,
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String newEmail)
            throws UserNotFoundException, EmailExistException, UsernameExistException {

        Employee updateEmployee = employeeService.updateEmployee(currentUsername, newUsername, newEmail);
        return new ResponseEntity<>(updateEmployee, OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteCustomerById(@RequestParam("id") Integer id) {
        employeeService.deleteEmployee(id);
        return build(NO_CONTENT, EMPLOYEE_DELETED_SUCCESSFULLY);
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
