package com.realdb.finalproject.relation;

import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.entity.sponsor.Sponsor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SEMI_SPONS")
public class SeminarSponsor {
    @EmbeddedId
    private SeminarSponsorId id;

    @MapsId("sponsorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_SPONSOR_ID", nullable = false)
    private Sponsor sponsor;

    @MapsId("seminarEventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEMINAR_EVENT_ID", nullable = false)
    private Seminar seminarEvent;

    @Column(name = "AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    public SeminarSponsorId getId() {
        return id;
    }

    public void setId(SeminarSponsorId id) {
        this.id = id;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Seminar getSeminarEvent() {
        return seminarEvent;
    }

    public void setSeminarEvent(Seminar seminarEvent) {
        this.seminarEvent = seminarEvent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}