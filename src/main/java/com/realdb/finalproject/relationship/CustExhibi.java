package com.realdb.finalproject.relationship;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.Exhibition;

import javax.persistence.*;

@Entity
@Table(name = "CUST_EXHIBI")
public class CustExhibi {
    @EmbeddedId
    private CustExhibiId id;

    @MapsId("customerCId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_C_ID", nullable = false)
    private Customer customerC;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EXHIBITION_EVENT_ID", nullable = false)
    private Exhibition exhibitionEvent;

    public CustExhibiId getId() {
        return id;
    }

    public void setId(CustExhibiId id) {
        this.id = id;
    }

    public Customer getCustomerC() {
        return customerC;
    }

    public void setCustomerC(Customer customerC) {
        this.customerC = customerC;
    }

    public Exhibition getExhibitionEvent() {
        return exhibitionEvent;
    }

    public void setExhibitionEvent(Exhibition exhibitionEvent) {
        this.exhibitionEvent = exhibitionEvent;
    }

}