package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "INDIVI_SPONSOR")
public class IndiviSponsor {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "INDIVI_SPONSOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "INDIVI_SPONSOR_SEQUENCE",
            sequenceName = "INDIVI_SPONSOR_SEQUENCE",
            initialValue = 14,
            allocationSize = 1)
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_ID", nullable = false)
    private Sponsor sponsor;

    @Column(name = "INDI_FNAME", nullable = false, length = 30)
    private String indiFname;

    @Column(name = "INDI_LNAME", nullable = false, length = 30)
    private String indiLname;

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

    public String getIndiFname() {
        return indiFname;
    }

    public void setIndiFname(String indiFname) {
        this.indiFname = indiFname;
    }

    public String getIndiLname() {
        return indiLname;
    }

    public void setIndiLname(String indiLname) {
        this.indiLname = indiLname;
    }

}