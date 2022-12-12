package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "SPONSOR")
public class Sponsor {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "SPONSOR_SEQUENCE"
    )
    @SequenceGenerator(
            name = "SPONSOR_SEQUENCE",
            sequenceName = "SPONSOR_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "SPONSOR_ID", nullable = false)
    private Integer id;

    @Column(name = "S_TYPE", nullable = false, length = 1)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}