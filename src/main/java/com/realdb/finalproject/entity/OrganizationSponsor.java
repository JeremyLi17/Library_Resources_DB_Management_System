package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ORGANI_SPONSOR")
public class OrganizationSponsor {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "SPONSOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "SPONSOR_SEQUENCE",
            sequenceName = "SPONSOR_SEQUENCE",
            allocationSize = 1)
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_ID", nullable = false)
    private Sponsor sponsor;

    @Column(name = "ORG_NAME", nullable = false, length = 50)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}