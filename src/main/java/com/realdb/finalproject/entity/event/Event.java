package com.realdb.finalproject.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "EVENT_SEQUENCE"
    )
    @SequenceGenerator(
            name = "EVENT_SEQUENCE",
            sequenceName = "EVENT_SEQUENCE",
            allocationSize = 1)
    @Column(name = "EVENT_ID", nullable = false)
    private Integer id;

    @Column(name = "EVENT_NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "EVENT_TYPE", nullable = false)
    private String type;

    @Column(name = "START_DATETIME", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate startAt;

    @Column(name = "STOP_DATETIME", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd",
            without = {ADJUST_DATES_TO_CONTEXT_TIME_ZONE}
    )
    private LocalDate stopAt;

    @Column(name = "EVENT_TOPIC", nullable = false, length = 30)
    private String topic;

    public Event() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getStopAt() {
        return stopAt;
    }

    public void setStopAt(LocalDate stopAt) {
        this.stopAt = stopAt;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}