package com.realdb.finalproject.entity.studyroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StudyRoomRepo extends JpaRepository <StudyRoom, Integer>{

    @Query("SELECT s FROM StudyRoom s " +
            "WHERE s.id NOT IN " +
            "(SELECT r.studyRoom.id FROM Reservation r " +
            "WHERE r.date = ?1 AND r.timeslot = ?2)")
    List<StudyRoom> findAvailableRoomByDate(LocalDate date, String timeslot);
}
