package com.realdb.finalproject.relation.seminarSponsor;

import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.entity.sponsor.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
public interface SeminarSponsorRepo extends JpaRepository<SeminarSponsor, SeminarSponsorId> {
    @Query("SELECT s FROM Sponsor s WHERE s.id IN " +
            "(SELECT ss.sponsor.id FROM SeminarSponsor ss WHERE ss.seminarEvent.id= ?1)")
    List<Sponsor> getAllSponsorForSeminar(Integer id);

    @Query("SELECT s FROM Seminar s WHERE s.id IN " +
            "(SELECT ss.seminarEvent.id FROM SeminarSponsor ss WHERE ss.sponsor.id= ?1)")
    List<Seminar> getAllSeminarForSponsor(Integer sponsorId);
}
