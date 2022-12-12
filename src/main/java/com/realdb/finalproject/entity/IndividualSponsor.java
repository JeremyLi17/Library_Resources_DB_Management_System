package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "INDIVI_SPONSOR")
public class IndividualSponsor {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "INDIVI_SPONSOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "INDIVI_SPONSOR_SEQUENCE",
            sequenceName = "INDIVI_SPONSOR_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPONSOR_ID", nullable = false)
    private Sponsor sponsor;

    @Column(name = "INDI_FNAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "INDI_LNAME", nullable = false, length = 30)
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}