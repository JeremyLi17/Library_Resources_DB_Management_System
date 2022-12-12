package com.realdb.finalproject.entity.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
public interface SeminarRepo extends JpaRepository<Seminar, Integer> {

    @Query("SELECT s FROM Seminar s WHERE s.event.topic = :topic")
    List<Seminar> getSeminarByTopic(@Param("topic") String topic);
}
