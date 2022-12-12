package com.realdb.finalproject.entity.event;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "SEMINAR")
public class Seminar {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "EVENT_SEQUENCE"
    )
    @SequenceGenerator(
            name = "EVENT_SEQUENCE",
            sequenceName = "EVENT_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "EVENT_ID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}