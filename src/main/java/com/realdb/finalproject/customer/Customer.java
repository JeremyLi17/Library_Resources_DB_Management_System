package com.realdb.finalproject.customer;

import com.realdb.finalproject.domain.User;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends User {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "CUSTOMER_SEQUENCE"
    )
    @SequenceGenerator(
            name = "CUSTOMER_SEQUENCE",
            sequenceName = "CUSTOMER_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "C_ID", nullable = false)
    private Integer id;

    @Column(name = "C_FNAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "C_MNAME", length = 30)
    private String middleName;

    @Column(name = "C_LNAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "C_EMAIL", nullable = false, length = 30)
    private String email;

    @Column(name = "C_PHONENO", nullable = false)
    private String phoneNo;

    @Column(name = "ID_TYPE", nullable = false, length = 1)
    private String idType;

    @Column(name = "ID_NO", nullable = false, length = 15)
    private String idNo;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean isNotLocked;
    private boolean isActive;

    @Column(nullable = false)
    private String Role;

    public Customer() {}

    public Customer(String firstName,
                    String middleName,
                    String lastName,
                    String email,
                    String phoneNo,
                    String idType,
                    String idNo,
                    String username,
                    String password,
                    boolean isNotLocked,
                    boolean isActive,
                    String role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.idType = idType;
        this.idNo = idNo;
        this.username = username;
        this.password = password;
        this.isNotLocked = isNotLocked;
        this.isActive = isActive;
        this.Role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String[] getAuthorities() {
        return new String[]{this.getRole()};
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String CMname) {
        this.middleName = CMname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String CLname) {
        this.lastName = CLname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}