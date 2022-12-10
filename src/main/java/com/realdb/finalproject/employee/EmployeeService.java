package com.realdb.finalproject.employee;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.domain.Role;
import com.realdb.finalproject.domain.User;
import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
import com.realdb.finalproject.security.LoginAttemptService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author jeremy on 2022/12/10
 */
@Service
public class EmployeeService implements UserDetailsService {

    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: ";
    public static final String RETURNING_FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists: ";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists: ";

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EmployeeRepo employeeRepo;
    private final LoginAttemptService loginAttemptService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,
                           LoginAttemptService loginAttemptService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.loginAttemptService = loginAttemptService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Employee> findEmployeeByUsername(String username) {
        return employeeRepo.findEmployeeByUsername(username);
    }

    public Optional<Employee> findEmployeeByEmail(String username) {
        return employeeRepo.findEmployeeByEmail(username);
    }

    public Employee registerEmployee(String username,
                                     String email,
                                     String username1,
                                     String password)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setEmail(email);
        employee.setRole(Role.ROLE_EMPLOYEE.getText());
        employee.setActive(true);
        employee.setNotLocked(true);
        String encodePassword = passwordEncoder.encode(password);
        employee.setPassword(encodePassword);

        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeOpt = employeeRepo.findEmployeeByUsername(username);
        if (employeeOpt.isEmpty()) {
            logger.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            Employee user = employeeOpt.get();
            validateLoginAttempt(user);
            employeeRepo.save(user);

            UserPrincipal userPrincipal = new UserPrincipal(user);
            logger.info(RETURNING_FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    private Employee validateNewUsernameAndEmail(String currentUsername,
                                                 String newUsername,
                                                 String newEmail)
            throws UsernameExistException, EmailExistException, UserNotFoundException {
        Optional<Employee> employeeByNewUsername = findEmployeeByUsername(newUsername);
        Optional<Employee> employeeByNewEmail = findEmployeeByEmail(newEmail);
        if (isNotBlank(currentUsername)) {
            // update process
            Optional<Employee> currentEmployee = findEmployeeByUsername(currentUsername);
            if (currentEmployee.isEmpty()) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }

            if (employeeByNewUsername.isPresent() && !currentEmployee.get().getId()
                    .equals(employeeByNewUsername.get().getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (employeeByNewEmail.isPresent() && !employeeByNewEmail.get().getId()
                    .equals(employeeByNewEmail.get().getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentEmployee.get();
        } else {
            // new user
            if (employeeByNewUsername.isPresent()) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (employeeByNewEmail.isPresent()) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotLocked()) {
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
