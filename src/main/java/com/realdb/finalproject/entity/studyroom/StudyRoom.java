package com.realdb.finalproject.entity.studyroom;

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
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ROOM_ID", nullable = false)
    private Integer id;

    @Column(name = "ROOM_CAPACITY", nullable = false)
    private Integer capacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}