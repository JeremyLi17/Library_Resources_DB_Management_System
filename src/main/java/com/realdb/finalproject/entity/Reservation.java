package com.realdb.finalproject.entity;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.studyroom.StudyRoom;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "RESERVATION_SEQUENCE"
    )
    @SequenceGenerator(
            name = "RESERVATION_SEQUENCE",
            sequenceName = "RESERVATION_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "RES_ID", nullable = false)
    private Long id;

    @Column(name = "RES_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "RES_TIMESLOT", nullable = false, length = 1)
    private String timeslot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDY_ROOM_ID", nullable = false)
    private StudyRoom studyRoom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public StudyRoom getStudyRoom() {
        return studyRoom;
    }

    public void setStudyRoom(StudyRoom studyRoom) {
        this.studyRoom = studyRoom;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}