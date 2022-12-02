package com.realdb.finalproject.entity;

import com.realdb.finalproject.entity.Sponsor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ORGANI_SPONSOR")
public class OrganiSponsor {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ORGANIZE_SPONSOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "ORGANIZE_SPONSOR_SEQUENCE",
            sequenceName = "ORGANIZE_SPONSOR_SEQUENCE",
            initialValue = 11,
            allocationSize = 1)
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_ID", nullable = false)
    private Sponsor sponsor;

    @Column(name = "ORG_NAME", nullable = false, length = 50)
    private String orgName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}