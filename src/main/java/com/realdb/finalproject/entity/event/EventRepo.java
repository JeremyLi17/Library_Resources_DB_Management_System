package com.realdb.finalproject.entity.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {

    @Transactional
    @Query("SELECT e FROM Event e WHERE e.topic = :topic")
    List<Event> getEventByTopic(@Param("topic") String topic);
}
