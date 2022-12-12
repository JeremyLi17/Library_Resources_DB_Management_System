package com.realdb.finalproject.entity.sponsor;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.IndividualSponsorNotFoundException;
import com.realdb.finalproject.exception.domain.OrganizationSponsorNotFoundException;
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
@RequestMapping("/api/sponsor")
public class SponsorController {

    public static final String ORGANIZATION_SPONSOR_DELETED_SUCCESSFULLY = "Organization Sponsor deleted successfully";
    public static final String INDIVIDUAL_SPONSOR_DELETED_SUCCESSFULLY = "Individual Sponsor deleted successfully";
    private final SponsorService sponsorService;

    @Autowired
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Sponsor> findSponsorById(@PathVariable Integer id)
            throws SponsorNotFoundException {
        Sponsor sponsor = sponsorService.findSponsorById(id);
        return new ResponseEntity<>(sponsor, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Sponsor>> findAllSponsor() {
        List<Sponsor> sponsors = sponsorService.findAllSponsor();
        return new ResponseEntity<>(sponsors, OK);
    }

    @GetMapping("/list/individual")
    public ResponseEntity<List<IndividualSponsor>> findAllIndividualSponsor() {
        List<IndividualSponsor> individualSponsors = sponsorService.findAllIndividualSponsor();
        return new ResponseEntity<>(individualSponsors, OK);
    }

    @GetMapping("/list/organization")
    public ResponseEntity<List<OrganizationSponsor>> findAllOrganizationSponsor() {
        List<OrganizationSponsor> organizationSponsors = sponsorService.findAllOrganizationSponsor();
        return new ResponseEntity<>(organizationSponsors, OK);
    }

    @PostMapping("/add/individual")
    public ResponseEntity<IndividualSponsor> addIndividualSponsor(
            @RequestBody IndividualSponsor individualSponsor) {
        sponsorService.addIndividualSponsor(individualSponsor);
        return new ResponseEntity<>(individualSponsor, CREATED);
    }

    @PostMapping("/add/organization")
    public ResponseEntity<OrganizationSponsor> addOrganizationSponsor(
            @RequestBody OrganizationSponsor organizationSponsor) {
        sponsorService.addOrganizationSponsor(organizationSponsor);
        return new ResponseEntity<>(organizationSponsor, CREATED);
    }

    @DeleteMapping("/delete/organization")
    public ResponseEntity<HttpResponse> deleteOrganizationSponsor(@RequestParam("id") Integer id)
            throws OrganizationSponsorNotFoundException {
        sponsorService.deleteOrganizationSponsor(id);
        return BuildResponse.build(NO_CONTENT, ORGANIZATION_SPONSOR_DELETED_SUCCESSFULLY);
    }

    @DeleteMapping("/delete/individual")
    public ResponseEntity<HttpResponse> deleteIndividualSponsor(@RequestParam("id") Integer id)
            throws IndividualSponsorNotFoundException {
        sponsorService.deleteIndividualSponsor(id);
        return BuildResponse.build(NO_CONTENT, INDIVIDUAL_SPONSOR_DELETED_SUCCESSFULLY);
    }
}
