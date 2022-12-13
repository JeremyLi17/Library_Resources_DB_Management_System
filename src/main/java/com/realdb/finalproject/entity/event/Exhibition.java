package com.realdb.finalproject.entity.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "EXHIBITION")
public class Exhibition {
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Event event;

    @Column(name = "EXPENSE", nullable = false, precision = 15, scale = 2)
    private BigDecimal expense;

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

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

}