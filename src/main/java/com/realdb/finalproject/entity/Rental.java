package com.realdb.finalproject.entity;

import com.realdb.finalproject.customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "RENTAL")
public class Rental {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "RENTAL_SEQUENCE"
    )
    @SequenceGenerator(
            name = "RENTAL_SEQUENCE",
            sequenceName = "RENTAL_SEQUENCE",
            initialValue = 16,
            allocationSize = 1)
    @Column(name = "R_ID", nullable = false)
    private Long id;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "BORROW_DATE", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "EXP_RETURN_DATE", nullable = false)
    private LocalDate expReturnDate;

    @Column(name = "ACT_RETURN_DATE")
    private LocalDate actReturnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_C_ID", nullable = false)
    private Customer customerC;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COPY_COPY_ID", nullable = false)
    private Copy copyCopy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getExpReturnDate() {
        return expReturnDate;
    }

    public void setExpReturnDate(LocalDate expReturnDate) {
        this.expReturnDate = expReturnDate;
    }

    public LocalDate getActReturnDate() {
        return actReturnDate;
    }

    public void setActReturnDate(LocalDate actReturnDate) {
        this.actReturnDate = actReturnDate;
    }

    public Customer getCustomerC() {
        return customerC;
    }

    public void setCustomerC(Customer customerC) {
        this.customerC = customerC;
    }

    public Copy getCopyCopy() {
        return copyCopy;
    }

    public void setCopyCopy(Copy copyCopy) {
        this.copyCopy = copyCopy;
    }

}