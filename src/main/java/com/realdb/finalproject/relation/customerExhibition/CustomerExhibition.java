package com.realdb.finalproject.relation.customerExhibition;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.event.Exhibition;

import javax.persistence.*;

@Entity
@Table(name = "CUST_EXHIBI")
public class CustomerExhibition {
    @EmbeddedId
    private CustomerExhibitionId id;

    @MapsId("customerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EXHIBITION_EVENT_ID", nullable = false)
    private Exhibition exhibitionEvent;

    public CustomerExhibitionId getId() {
        return id;
    }

    public void setId(CustomerExhibitionId id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Exhibition getExhibitionEvent() {
        return exhibitionEvent;
    }

    public void setExhibitionEvent(Exhibition exhibitionEvent) {
        this.exhibitionEvent = exhibitionEvent;
    }

}