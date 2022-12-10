package com.realdb.finalproject.employee;

import com.realdb.finalproject.domain.User;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author jeremy on 2022/12/10
 */
@Entity
public class Employee extends User {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "EMPLOYEE_SEQUENCE"
    )
    @SequenceGenerator(
            name = "EMPLOYEE_SEQUENCE",
            sequenceName = "EMPLOYEE_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String Role;
    @Column(nullable = false)
    private boolean isActive;
    @Column(nullable = false)
    private boolean isNotLocked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String[] getAuthorities() {
        return new String[]{this.getRole()};
    }

    @Override
    public boolean isNotLocked() {
        return isNotLocked;
    }

    @Override
    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }
}
