package com.realdb.finalproject.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
            initialValue = 21,
            allocationSize = 1)
    @Column(name = "EVENT_ID", nullable = false)
    private Integer id;

    @Column(name = "EVENT_NAME", nullable = false, length = 200)
    private String eventName;

    @Column(name = "EVENT_TYPE", nullable = false)
    private Boolean eventType = false;

    @Column(name = "START_DAYTIME", nullable = false)
    private LocalDate startDaytime;

    @Column(name = "STOP_DATETIME", nullable = false)
    private LocalDate stopDatetime;

    @Column(name = "EVENT_TOPIC", nullable = false, length = 30)
    private String eventTopic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Boolean getEventType() {
        return eventType;
    }

    public void setEventType(Boolean eventType) {
        this.eventType = eventType;
    }

    public LocalDate getStartDaytime() {
        return startDaytime;
    }

    public void setStartDaytime(LocalDate startDaytime) {
        this.startDaytime = startDaytime;
    }

    public LocalDate getStopDatetime() {
        return stopDatetime;
    }

    public void setStopDatetime(LocalDate stopDatetime) {
        this.stopDatetime = stopDatetime;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(String eventTopic) {
        this.eventTopic = eventTopic;
    }

}