package com.realdb.finalproject.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "INVOICE_SEQUENCE"
    )
    @SequenceGenerator(
            name = "INVOICE_SEQUENCE",
            sequenceName = "INVOICE_SEQUENCE",
            initialValue = 11,
            allocationSize = 1)
    @Column(name = "RENTAL_R_ID", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RENTAL_R_ID", nullable = false)
    private Rental rental;

    @Column(name = "AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}