package com.realdb.finalproject.customer;

import com.realdb.finalproject.domain.Role;
import com.realdb.finalproject.domain.User;
import com.realdb.finalproject.domain.UserPrincipal;
import com.realdb.finalproject.exception.domain.EmailExistException;
import com.realdb.finalproject.exception.domain.UserNotFoundException;
import com.realdb.finalproject.exception.domain.UsernameExistException;
import com.realdb.finalproject.security.LoginAttemptService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@AllArgsConstructor
@Qualifier("customerService")
public class CustomerService implements UserDetailsService {
    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: ";
    public static final String RETURNING_FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists: ";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists: ";

    private final CustomerRepo customerRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LoginAttemptService loginAttemptService;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }
    public Optional<Customer> findCustomerByUsername(String username) {
        return customerRepo.findCustomerByUsername(username);
    }
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepo.findCustomerByEmail(email);
    }

    public Customer registerCustomer(String firstName,
                             String lastName,
                             String email,
                             String phoneNo,
                             String idType,
                             String idNo,
                             String username,
                             String password)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNo(phoneNo);
        customer.setIdType(idType);
        customer.setIdNo(idNo);
        customer.setRole(Role.ROLE_CUSTOMER.getText());

        customer.setActive(true);
        customer.setNotLocked(true);

        String encodePassword = passwordEncoder.encode(password);
        customer.setPassword(encodePassword);
        customerRepo.save(customer);
        return customer;
    }

    @Transactional
    public Customer updateCustomer(String currentUsername,
                                   String newUsername,
                                   String newEmail,
                                   String newFirstName,
                                   String newLastName,
                                   String newMiddleName,
                                   String newPhoneNo,
                                   String newIdType,
                                   String newIdNo)
            throws UserNotFoundException, EmailExistException, UsernameExistException {
        Customer currentCustomer = validateNewUsernameAndEmail(currentUsername,
                newUsername, newEmail);

        assert currentCustomer != null;
        logger.info("here");
        if (StringUtils.isNotBlank(newUsername)) currentCustomer.setUsername(newUsername);
        if (StringUtils.isNotBlank(newFirstName)) currentCustomer.setFirstName(newFirstName);
        if (StringUtils.isNotBlank(newLastName)) currentCustomer.setLastName(newLastName);
        if (StringUtils.isNotBlank(newMiddleName)) currentCustomer.setEmail(newMiddleName);
        if (StringUtils.isNotBlank(newPhoneNo)) currentCustomer.setPhoneNo(newPhoneNo);
        if (StringUtils.isNotBlank(newIdType)) currentCustomer.setIdType(newIdType);
        if (StringUtils.isNotBlank(newIdNo)) currentCustomer.setIdNo(newIdNo);

        customerRepo.save(currentCustomer);
        return currentCustomer;
    }

    public Customer resetPassword(String username, String newpassword) {
        Optional<Customer> customerOpt = findCustomerByUsername(username);
        if (customerOpt.isEmpty()) {
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        }
        Customer customer = customerOpt.get();
        customer.setPassword(passwordEncoder.encode(newpassword));
        customerRepo.save(customer);
        return customer;
    }

    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    private Customer validateNewUsernameAndEmail(String currentUsername,
                                             String newUsername,
                                             String newEmail)
            throws UsernameExistException, EmailExistException, UserNotFoundException {
        Optional<Customer> customerByNewUsername = findCustomerByUsername(newUsername);
        Optional<Customer> customerByNewEmail = findCustomerByEmail(newEmail);
        if (isNotBlank(currentUsername)) {
            // update process
            Optional<Customer> currentCustomer = findCustomerByUsername(currentUsername);
            if (currentCustomer.isEmpty()) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }

            if (customerByNewUsername.isPresent() && !currentCustomer.get().getId()
                    .equals(customerByNewUsername.get().getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (customerByNewEmail.isPresent() && !customerByNewEmail.get().getId()
                    .equals(customerByNewEmail.get().getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentCustomer.get();
        } else {
            // new user
            if (customerByNewUsername.isPresent()) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if (customerByNewEmail.isPresent()) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userOpt = findCustomerByUsername(username);
        if (userOpt.isEmpty()) {
            logger.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            Customer user = userOpt.get();
            validateLoginAttempt(user);
            customerRepo.save(user);

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
