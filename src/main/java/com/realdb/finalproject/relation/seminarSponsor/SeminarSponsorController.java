package com.realdb.finalproject.relation.seminarSponsor;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.entity.sponsor.Sponsor;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
import com.realdb.finalproject.exception.domain.SponsorNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/seminarSponsor")
public class SeminarSponsorController {

    public static final String CUSTOMER_EXHIBITION_DELETED_SUCCESSFULLY = "Customer Exhibition deleted successfully";
    private final SeminarSponsorService seminarSponsorService;

    @Autowired
    public SeminarSponsorController(SeminarSponsorService seminarSponsorService) {
        this.seminarSponsorService = seminarSponsorService;
    }

    @GetMapping("/list/sponsor/{id}")
    public ResponseEntity<List<Sponsor>> getAllSponsorForSeminar(
            @PathVariable("id") Integer seminarId) throws SeminarNotFoundException {
        List<Sponsor> sponsors = seminarSponsorService.getAllSponsorForSeminar(seminarId);
        return new ResponseEntity<>(sponsors, OK);
    }

    @GetMapping("/list/seminar/{id}")
    public ResponseEntity<List<Seminar>> getAllSeminarForSponsor(
            @PathVariable("id") Integer sponsorId) throws SponsorNotFoundException {
        List<Seminar> seminars = seminarSponsorService.getAllSeminarForSponsor(sponsorId);
        return new ResponseEntity<>(seminars, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SeminarSponsor> addSeminarSponsorId(
            @RequestBody SeminarSponsor seminarSponsor)
            throws SeminarNotFoundException, SponsorNotFoundException {
        seminarSponsorService.addSeminarSponsorId(
                seminarSponsor.getSponsor().getId(),
                seminarSponsor.getSeminarEvent().getId()
        );
        return new ResponseEntity<>(seminarSponsor, CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteSeminarSponsor(
            @RequestParam("id") SeminarSponsorId seminarSponsorId) {
        seminarSponsorService.deleteSeminarSponsor(seminarSponsorId);
        return BuildResponse.build(NO_CONTENT, CUSTOMER_EXHIBITION_DELETED_SUCCESSFULLY);
    }
}
