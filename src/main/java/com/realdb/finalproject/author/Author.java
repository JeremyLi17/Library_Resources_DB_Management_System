package com.realdb.finalproject.author;

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

    public String getAFname() {
        return firstName;
    }

    public void setAFname(String aFname) {
        this.firstName = aFname;
    }

    public String getALname() {
        return lastName;
    }

    public void setALname(String aLname) {
        this.lastName = aLname;
    }

    public String getAStreet() {
        return street;
    }

    public void setAStreet(String aStreet) {
        this.street = aStreet;
    }

    public String getACity() {
        return city;
    }

    public void setACity(String aCity) {
        this.city = aCity;
    }

    public String getACountry() {
        return country;
    }

    public void setACountry(String aCountry) {
        this.country = aCountry;
    }

    public String getAZipcode() {
        return zipcode;
    }

    public void setAZipcode(String aZipcode) {
        this.zipcode = aZipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}