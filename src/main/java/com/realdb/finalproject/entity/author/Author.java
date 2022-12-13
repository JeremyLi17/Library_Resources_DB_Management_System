package com.realdb.finalproject.entity.author;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "AUTHOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "AUTHOR_SEQUENCE",
            sequenceName = "AUTHOR_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "AUTHOR_ID", nullable = false)
    private Integer id;

    @Column(name = "A_FNAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "A_LNAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "A_STREET", nullable = false, length = 100)
    private String street;

    @Column(name = "A_CITY", nullable = false, length = 30)
    private String city;

    @Column(name = "A_COUNTRY", nullable = false, length = 30)
    private String country;

    @Column(name = "A_ZIPCODE", nullable = false)
    private String zipcode;

    @Column(name = "A_EMAIL", length = 30)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}