package com.realdb.finalproject.relation.seminarSponsor;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeminarSponsorId implements Serializable {
    private static final long serialVersionUID = -2269424324792490002L;
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer sponsorId;

    @Column(name = "SEMINAR_EVENT_ID", nullable = false)
    private Integer seminarEventId;

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
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
        SeminarSponsorId entity = (SeminarSponsorId) o;
        return Objects.equals(this.sponsorId, entity.sponsorId) &&
                Objects.equals(this.seminarEventId, entity.seminarEventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sponsorId, seminarEventId);
    }

}