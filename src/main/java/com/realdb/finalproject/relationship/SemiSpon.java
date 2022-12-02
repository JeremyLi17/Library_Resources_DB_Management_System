package com.realdb.finalproject.relationship;

import com.realdb.finalproject.entity.Seminar;
import com.realdb.finalproject.entity.Sponsor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SEMI_SPONS")
public class SemiSpon {
    @EmbeddedId
    private SemiSponId id;

    @MapsId("sponsorSponsorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_SPONSOR_ID", nullable = false)
    private Sponsor sponsorSponsor;

    @MapsId("seminarEventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEMINAR_EVENT_ID", nullable = false)
    private Seminar seminarEvent;

    @Column(name = "AMOUNT", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    public SemiSponId getId() {
        return id;
    }

    public void setId(SemiSponId id) {
        this.id = id;
    }

    public Sponsor getSponsorSponsor() {
        return sponsorSponsor;
    }

    public void setSponsorSponsor(Sponsor sponsorSponsor) {
        this.sponsorSponsor = sponsorSponsor;
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