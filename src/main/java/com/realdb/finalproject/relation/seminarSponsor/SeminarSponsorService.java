package com.realdb.finalproject.relation.seminarSponsor;

import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.entity.event.Exhibition;
import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.entity.event.SeminarRepo;
import com.realdb.finalproject.entity.sponsor.Sponsor;
import com.realdb.finalproject.entity.sponsor.SponsorRepo;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
import com.realdb.finalproject.exception.domain.SponsorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class SeminarSponsorService {
    private final SeminarSponsorRepo seminarSponsorRepo;
    private final SeminarRepo seminarRepo;
    private final SponsorRepo sponsorRepo;

    @Autowired
    public SeminarSponsorService(SeminarSponsorRepo seminarSponsorRepo,
                                 SeminarRepo seminarRepo,
                                 SponsorRepo sponsorRepo) {
        this.seminarSponsorRepo = seminarSponsorRepo;
        this.seminarRepo = seminarRepo;
        this.sponsorRepo = sponsorRepo;
    }

    public List<Sponsor> getAllSponsorForSeminar(Integer seminarId) throws SeminarNotFoundException {
        Optional<Seminar> seminarOpt = seminarRepo.findById(seminarId);
        if (seminarOpt.isEmpty()) {
            throw new SeminarNotFoundException("Seminar: " + seminarId + " not found");
        }
        List<Sponsor> sponsors = seminarSponsorRepo.getAllSponsorForSeminar(seminarId);
        return sponsors;
    }

    public List<Seminar> getAllSeminarForSponsor(Integer sponsorId) throws SponsorNotFoundException {
        Optional<Sponsor> sponsorOpt = sponsorRepo.findById(sponsorId);
        if (sponsorOpt.isEmpty()) {
            throw new SponsorNotFoundException("Sponsor: " + sponsorId + " not found");
        }
        List<Seminar> seminars = seminarSponsorRepo.getAllSeminarForSponsor(sponsorId);
        return seminars;
    }

    public void deleteSeminarSponsor(SeminarSponsorId seminarSponsorId) {
        seminarSponsorRepo.deleteById(seminarSponsorId);
    }

    public SeminarSponsor addSeminarSponsorId(Integer sponsorId, Integer seminarId)
            throws SeminarNotFoundException, SponsorNotFoundException {
        Optional<Seminar> seminarOpt = seminarRepo.findById(seminarId);
        if (seminarOpt.isEmpty()) {
            throw new SeminarNotFoundException("Seminar: " + seminarId + " not found");
        }
        Optional<Sponsor> sponsorOpt = sponsorRepo.findById(sponsorId);
        if (sponsorOpt.isEmpty()) {
            throw new SponsorNotFoundException("Sponsor: " + sponsorId + " not found");
        }
        SeminarSponsor seminarSponsor = new SeminarSponsor();
        seminarSponsor.setSponsor(sponsorOpt.get());;
        seminarSponsor.setSeminarEvent(seminarOpt.get());;
        seminarSponsorRepo.save(seminarSponsor);
        return seminarSponsor;
    }
}
