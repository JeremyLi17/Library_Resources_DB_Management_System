package com.realdb.finalproject.relation.authorSeminar;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.event.Seminar;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
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
@RequestMapping("/api/authorSeminar")
public class AuthorSeminarController {
    public static final String AUTHOR_SEMINAR_DELETED_SUCCESSFULLY = "Author Seminar deleted successfully";
    public final AuthorSeminarService authorSeminarService;

    @Autowired
    public AuthorSeminarController(AuthorSeminarService authorSeminarService) {
        this.authorSeminarService = authorSeminarService;
    }

    @GetMapping("/find/{firstName}-{lastName}")
    public ResponseEntity<List<Seminar>> getSeminarByAuthorName(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) throws AuthorNotFoundException {
        List<Seminar> seminars = authorSeminarService.getSeminarByAuthorName(firstName, lastName);
        return new ResponseEntity<>(seminars, OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Author>> getAuthorBySeminarId(
            @PathVariable("id") Integer id) throws SeminarNotFoundException {
        List<Author> authors = authorSeminarService.getAuthorBySeminarId(id);
        return new ResponseEntity<>(authors, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorSeminar> addAuthorSeminar(
            @RequestBody AuthorSeminar authorSeminar)
            throws AuthorNotFoundException, SeminarNotFoundException {
        authorSeminarService.addAuthorSeminar(authorSeminar.getAuthor().getId(),
                authorSeminar.getSeminarEvent().getId());
        return new ResponseEntity<>(authorSeminar, CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteAuthorSeminar(
            @RequestParam("id") AuthorSeminarId authorSeminarId) {
        authorSeminarService.deleteAuthorSeminar(authorSeminarId);
        return BuildResponse.build(NO_CONTENT, AUTHOR_SEMINAR_DELETED_SUCCESSFULLY);
    }
}
