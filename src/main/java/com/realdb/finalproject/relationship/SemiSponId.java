package com.realdb.finalproject.relationship;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SemiSponId implements Serializable {
    private static final long serialVersionUID = -2269424324792490002L;
    @Column(name = "SPONSOR_SPONSOR_ID", nullable = false)
    private Integer sponsorSponsorId;

    @Column(name = "SEMINAR_EVENT_ID", nullable = false)
    private Integer seminarEventId;

    public Integer getSponsorSponsorId() {
        return sponsorSponsorId;
    }

    public void setSponsorSponsorId(Integer sponsorSponsorId) {
        this.sponsorSponsorId = sponsorSponsorId;
    }

    public Integer getSeminarEventId() {
        return seminarEventId;
    }

    public void setSeminarEventId(Integer seminarEventId) {
        this.seminarEventId = seminarEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SemiSponId entity = (SemiSponId) o;
        return Objects.equals(this.sponsorSponsorId, entity.sponsorSponsorId) &&
                Objects.equals(this.seminarEventId, entity.seminarEventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sponsorSponsorId, seminarEventId);
    }

}