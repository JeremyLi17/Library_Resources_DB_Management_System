package com.realdb.finalproject.entity.Rental;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.copy.Copy;

import javax.persistence.*;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
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
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "R_ID", nullable = false)
    private Long id;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "BORROW_DATE", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate borrowDate;

    @Column(name = "EXP_RETURN_DATE", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate expReturnDate;

    @Column(name = "ACT_RETURN_DATE")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate actReturnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COPY_ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Copy copy;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }
}