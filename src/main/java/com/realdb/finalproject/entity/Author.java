package com.realdb.finalproject.entity;

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
            initialValue = 13,
            allocationSize = 1)
    @Column(name = "AUTHOR_ID", nullable = false)
    private Integer id;

    @Column(name = "A_FNAME", nullable = false, length = 30)
    private String aFname;

    @Column(name = "A_LNAME", nullable = false, length = 30)
    private String aLname;

    @Column(name = "A_STREET", nullable = false, length = 100)
    private String aStreet;

    @Column(name = "A_CITY", nullable = false, length = 30)
    private String aCity;

    @Column(name = "A_COUNTRY", nullable = false, length = 30)
    private String aCountry;

    @Column(name = "A_ZIPCODE", nullable = false)
    private Integer aZipcode;

    @Column(name = "A_EMAIL", length = 30)
    private String aemail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAFname() {
        return aFname;
    }

    public void setAFname(String aFname) {
        this.aFname = aFname;
    }

    public String getALname() {
        return aLname;
    }

    public void setALname(String aLname) {
        this.aLname = aLname;
    }

    public String getAStreet() {
        return aStreet;
    }

    public void setAStreet(String aStreet) {
        this.aStreet = aStreet;
    }

    public String getACity() {
        return aCity;
    }

    public void setACity(String aCity) {
        this.aCity = aCity;
    }

    public String getACountry() {
        return aCountry;
    }

    public void setACountry(String aCountry) {
        this.aCountry = aCountry;
    }

    public Integer getAZipcode() {
        return aZipcode;
    }

    public void setAZipcode(Integer aZipcode) {
        this.aZipcode = aZipcode;
    }

    public String getAEmail() {
        return aemail;
    }

    public void setAEmail(String aEmail) {
        this.aemail = aEmail;
    }

}