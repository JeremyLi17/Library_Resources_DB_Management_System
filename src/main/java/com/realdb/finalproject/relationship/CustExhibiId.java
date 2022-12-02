package com.realdb.finalproject.relationship;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustExhibiId implements Serializable {
    private static final long serialVersionUID = -7689577400550935944L;
    @Column(name = "REGISTRATION_ID", nullable = false)
    private Integer registrationId;

    @Column(name = "CUSTOMER_C_ID", nullable = false)
    private Integer customerCId;

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getCustomerCId() {
        return customerCId;
    }

    public void setCustomerCId(Integer customerCId) {
        this.customerCId = customerCId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustExhibiId entity = (CustExhibiId) o;
        return Objects.equals(this.customerCId, entity.customerCId) &&
                Objects.equals(this.registrationId, entity.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerCId, registrationId);
    }

}