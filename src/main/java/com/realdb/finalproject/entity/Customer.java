package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "CUSTOMER_SEQUENCE"
    )
    @SequenceGenerator(
            name = "CUSTOMER_SEQUENCE",
            sequenceName = "CUSTOMER_SEQUENCE",
            initialValue = 14,
            allocationSize = 1)
    @Column(name = "C_ID", nullable = false)
    private Integer id;

    @Column(name = "C_FNAME", nullable = false, length = 30)
    private String cFname;

    @Column(name = "C_MNAME", length = 30)
    private String cMname;

    @Column(name = "C_LNAME", nullable = false, length = 30)
    private String cLname;

    @Column(name = "C_EMAIL", nullable = false, length = 30)
    private String cEmail;

    @Column(name = "C_PHONENO", nullable = false)
    private Long cPhoneno;

    @Column(name = "ID_TYPE", nullable = false, length = 1)
    private String idType;

    @Column(name = "ID_NO", nullable = false, length = 15)
    private String idNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCFname() {
        return cFname;
    }

    public void setCFname(String cFname) {
        this.cFname = cFname;
    }

    public String getCMname() {
        return cMname;
    }

    public void setCMname(String cMname) {
        this.cMname = cMname;
    }

    public String getCLname() {
        return cLname;
    }

    public void setCLname(String cLname) {
        this.cLname = cLname;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public Long getCPhoneno() {
        return cPhoneno;
    }

    public void setCPhoneno(Long cPhoneno) {
        this.cPhoneno = cPhoneno;
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