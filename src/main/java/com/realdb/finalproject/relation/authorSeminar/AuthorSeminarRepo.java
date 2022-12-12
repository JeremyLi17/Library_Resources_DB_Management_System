package com.realdb.finalproject.relation.authorSeminar;

import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.event.Seminar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
public interface AuthorSeminarRepo extends JpaRepository<AuthorSeminar, AuthorSeminarId> {

    @Query("SELECT s FROM Seminar s WHERE s.id IN " +
            "(SELECT asr.seminarEvent.id FROM AuthorSeminar asr WHERE asr.author.id = :id)")
    List<Seminar> getSeminarByAuthorId(@Param("id") Integer id);

    @Query("SELECT a FROM Author a WHERE a.id IN " +
            "(SELECT asr.author.id FROM AuthorSeminar asr WHERE asr.seminarEvent = : id)")
    List<Author> getAuthorBySeminarId(Integer id);
}
