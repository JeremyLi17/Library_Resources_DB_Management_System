package com.realdb.finalproject.employee;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author jeremy on 2022/12/10
 */
@Service
@Qualifier("employeeService")
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
                                     String password)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        Employee employee = new Employee();

        employee.setUsername(username);
        employee.setEmail(email);
        String encodePassword = passwordEncoder.encode(password);
        employee.setPassword(encodePassword);
        employee.setRole(Role.ROLE_EMPLOYEE.getText());
        employee.setActive(true);
        employee.setNotLocked(true);

        employeeRepo.save(employee);
        return employee;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee resetPassword(String username, String newpassword) {
        Optional<Employee> employeeOpt = findEmployeeByUsername(username);
        if (employeeOpt.isEmpty()) {
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        }
        Employee employee = employeeOpt.get();
        employee.setPassword(passwordEncoder.encode(newpassword));
        employeeRepo.save(employee);
        return employee;
    }

    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }

    @Transactional
    public Employee updateEmployee(String currentUsername, String newUsername, String newEmail)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        Employee updatedEmployee = validateNewUsernameAndEmail(currentUsername,
                newUsername, newEmail);

        assert updatedEmployee != null;
        if (StringUtils.isNotBlank(newUsername)) updatedEmployee.setUsername(newUsername);
        if (StringUtils.isNotBlank(newEmail)) updatedEmployee.setUsername(newEmail);

        employeeRepo.save(updatedEmployee);
        return updatedEmployee;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeOpt = findEmployeeByUsername(username);
        if (employeeOpt.isEmpty()) {
            logger.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            Employee employee = employeeOpt.get();
            validateLoginAttempt(employee);
            employeeRepo.save(employee);

            UserPrincipal userPrincipal = new UserPrincipal(employee);
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
