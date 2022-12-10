package com.realdb.finalproject.employee;

import com.realdb.finalproject.domain.User;
import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

//    public Employee registerEmployee() {
//
//    }

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
