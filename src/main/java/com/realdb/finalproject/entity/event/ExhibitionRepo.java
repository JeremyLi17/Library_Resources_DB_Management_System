package com.realdb.finalproject.entity.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
public interface ExhibitionRepo extends JpaRepository<Exhibition, Integer> {
    @Query("SELECT e FROM Exhibition e WHERE e.event.topic = :topic")
    List<Exhibition> getExhibitionByTopic(@Param("topic") String topic);
}
