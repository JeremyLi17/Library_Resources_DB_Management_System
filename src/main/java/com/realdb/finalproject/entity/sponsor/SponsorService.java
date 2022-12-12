package com.realdb.finalproject.entity.sponsor;

import com.realdb.finalproject.exception.domain.IndividualSponsorNotFoundException;
import com.realdb.finalproject.exception.domain.OrganizationSponsorNotFoundException;
import com.realdb.finalproject.exception.domain.SponsorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class SponsorService {

    private final SponsorRepo sponsorRepo;
    private final IndividualSponsorRepo individualSponsorRepo;
    private final OrganizationSponsorRepo organizationSponsorRepo;

    @Autowired
    public SponsorService(SponsorRepo sponsorRepo,
                          IndividualSponsorRepo individualSponsorRepo,
                          OrganizationSponsorRepo organizationSponsorRepo) {
        this.sponsorRepo = sponsorRepo;
        this.individualSponsorRepo = individualSponsorRepo;
        this.organizationSponsorRepo = organizationSponsorRepo;
    }

    public Sponsor findSponsorById(Integer id) throws SponsorNotFoundException {
        Optional<Sponsor> sponsorOpt = sponsorRepo.findById(id);
        if (sponsorOpt.isEmpty()) {
            throw new SponsorNotFoundException("Sponsor with id: " + id + " not found");
        }
        return sponsorOpt.get();
    }

    public List<Sponsor> findAllSponsor() {
        return sponsorRepo.findAll();
    }

    public List<IndividualSponsor> findAllIndividualSponsor() {
        return individualSponsorRepo.findAll();
    }

    public List<OrganizationSponsor> findAllOrganizationSponsor() {
        return organizationSponsorRepo.findAll();
    }

    public void addIndividualSponsor(IndividualSponsor individualSponsor) {
        sponsorRepo.save(individualSponsor.getSponsor());
        individualSponsorRepo.save(individualSponsor);
    }

    public void addOrganizationSponsor(OrganizationSponsor organizationSponsor) {
        sponsorRepo.save(organizationSponsor.getSponsor());
        organizationSponsorRepo.save(organizationSponsor);
    }

    public void deleteOrganizationSponsor(Integer id) throws OrganizationSponsorNotFoundException {
        Optional<OrganizationSponsor> organizationSponsorOpt = organizationSponsorRepo.findById(id);
        if (organizationSponsorOpt.isEmpty()) {
            throw new OrganizationSponsorNotFoundException(
                    "Organization sponsor with id: " +  id + "not found");
        }
        organizationSponsorRepo.deleteById(id);
        sponsorRepo.deleteById(id);
    }

    public void deleteIndividualSponsor(Integer id) throws IndividualSponsorNotFoundException {
        Optional<IndividualSponsor> individualSponsorOpt = individualSponsorRepo.findById(id);
        if (individualSponsorOpt.isEmpty()) {
            throw new IndividualSponsorNotFoundException(
                    "Organization sponsor with id: " +  id + "not found");
        }
        organizationSponsorRepo.deleteById(id);
        sponsorRepo.deleteById(id);
    }
}
