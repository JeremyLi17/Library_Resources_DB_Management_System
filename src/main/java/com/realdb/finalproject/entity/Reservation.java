package com.realdb.finalproject.entity;

import com.realdb.finalproject.customer.Customer;

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
            initialValue = 11,
            allocationSize = 1)
    @Column(name = "RES_ID", nullable = false)
    private Long id;

    @Column(name = "RES_DATE", nullable = false)
    private LocalDate resDate;

    @Column(name = "RES_TIMESLOT", nullable = false, length = 1)
    private String resTimeslot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDY_ROOM_ROOM_ID", nullable = false)
    private StudyRoom studyRoomRoom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_C_ID", nullable = false)
    private Customer customerC;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getResDate() {
        return resDate;
    }

    public void setResDate(LocalDate resDate) {
        this.resDate = resDate;
    }

    public String getResTimeslot() {
        return resTimeslot;
    }

    public void setResTimeslot(String resTimeslot) {
        this.resTimeslot = resTimeslot;
    }

    public StudyRoom getStudyRoomRoom() {
        return studyRoomRoom;
    }

    public void setStudyRoomRoom(StudyRoom studyRoomRoom) {
        this.studyRoomRoom = studyRoomRoom;
    }

    public Customer getCustomerC() {
        return customerC;
    }

    public void setCustomerC(Customer customerC) {
        this.customerC = customerC;
    }

}