package com.realdb.finalproject.relation.customerExhibition;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerExhibitionId implements Serializable {
    private static final long serialVersionUID = -7689577400550935944L;
    @Column(name = "REGISTRATION_ID", nullable = false)
    private Integer registrationId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerCId) {
        this.customerId = customerCId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerExhibitionId entity = (CustomerExhibitionId) o;
        return Objects.equals(this.customerId, entity.customerId) &&
                Objects.equals(this.registrationId, entity.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, registrationId);
    }

}