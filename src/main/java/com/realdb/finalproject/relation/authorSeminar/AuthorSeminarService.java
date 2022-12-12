package com.realdb.finalproject.relation.authorSeminar;

import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.author.AuthorRepo;
import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.entity.event.SeminarRepo;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class AuthorSeminarService {
    private final AuthorRepo authorRepo;
    private final AuthorSeminarRepo authorSeminarRepo;
    private final SeminarRepo seminarRepo;

    @Autowired
    public AuthorSeminarService(AuthorRepo authorRepo,
                                AuthorSeminarRepo authorSeminarRepo,
                                SeminarRepo seminarRepo) {
        this.authorRepo = authorRepo;
        this.authorSeminarRepo = authorSeminarRepo;
        this.seminarRepo = seminarRepo;
    }

    public List<Seminar> getSeminarByAuthorName(String lastName, String firstName)
            throws AuthorNotFoundException {
        Optional<Author> authorOpt = authorRepo.findAuthorByLastNameAndFirstName(lastName, firstName);
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException("Author: " + lastName + " " + firstName + " not found");
        }
        Author author = authorOpt.get();
        List<Seminar> seminars = authorSeminarRepo.getSeminarByAuthorId(author.getId());
        return seminars;
    }

    public List<Author> getAuthorBySeminarId(Integer seminarId) throws SeminarNotFoundException {
        Optional<Seminar> seminarOpt = seminarRepo.findById(seminarId);
        if (seminarOpt.isEmpty()) {
            throw new SeminarNotFoundException("Seminar with id : " + seminarId + " not found");
        }
        return authorSeminarRepo.getAuthorBySeminarId(seminarId);
    }

    public void addAuthorSeminar(Integer authorId, Integer seminarId)
            throws AuthorNotFoundException, SeminarNotFoundException {
        AuthorSeminar authorSeminar = new AuthorSeminar();
        Optional<Author> authorOpt = authorRepo.findById(authorId);
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException("Author with id: " + authorId + " not found");
        }
        authorSeminar.setAuthor(authorOpt.get());

        Optional<Seminar> seminarOpt = seminarRepo.findById(seminarId);
        if (seminarOpt.isEmpty()) {
            throw new SeminarNotFoundException("Seminar with id: " + seminarId + " not found");
        }
        authorSeminar.setSeminarEvent(seminarOpt.get());
        authorSeminarRepo.save(authorSeminar);
    }

    public void deleteAuthorSeminar(AuthorSeminarId authorSeminarId) {
        authorSeminarRepo.deleteById(authorSeminarId);
    }
}
