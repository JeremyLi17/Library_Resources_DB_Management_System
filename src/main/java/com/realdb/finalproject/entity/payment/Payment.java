package com.realdb.finalproject.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realdb.finalproject.entity.Invoice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "PAYMENT_SEQUENCE"
    )
    @SequenceGenerator(
            name = "PAYMENT_SEQUENCE",
            sequenceName = "PAYMENT_SEQUENCE",
            initialValue = 11,
            allocationSize = 1)
    @Column(name = "PAYMENT_ID", nullable = false)
    private Integer id;

    @Column(name = "PAYMENT_METHOD", nullable = false, length = 10)
    private String method;

    @Column(name = "PAYMENT_DATE", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate paymentDate;

    @Column(name = "CARD_HOLDER_FULLNAME", length = 30)
    private String cardHolderFullName;

    @Column(name = "PAYMENT_AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal paymentAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private Invoice invoice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCardHolderFullName() {
        return cardHolderFullName;
    }

    public void setCardHolderFullName(String cardHolderFullName) {
        this.cardHolderFullName = cardHolderFullName;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}