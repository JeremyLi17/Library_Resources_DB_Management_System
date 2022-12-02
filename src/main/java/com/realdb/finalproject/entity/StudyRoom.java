package com.realdb.finalproject.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "STUDY_ROOM")
public class StudyRoom {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "STUDY_ROOM_SEQUENCE"
    )
    @SequenceGenerator(
            name = "STUDY_ROOM_SEQUENCE",
            sequenceName = "STUDY_ROOM_SEQUENCE",
            initialValue = 11,
            allocationSize = 1)
    @Column(name = "ROOM_ID", nullable = false)
    private Short id;

    @Column(name = "ROOM_CAPACITY", nullable = false)
    private Short roomCapacity;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Short roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

}