package com.realdb.finalproject.entity.author;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

import static com.realdb.finalproject.utility.BuildResponse.build;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author jeremy on 2022/12/2
 */
@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {

    public static final String AUTHOR_DELETED_SUCCESSFUL = "Author deleted successful";

    private final AuthorService authorService;

    @GetMapping("/list")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/find/{firstName}-{lastName}")
    public ResponseEntity<Author> getAuthorByName(
            @PathVariable("lastName") String lastName,
            @PathVariable("firstName") String firstName) throws AuthorNotFoundException {
        Author author = authorService.getAuthor(lastName, firstName);
        return new ResponseEntity<>(author, OK);
    }

    @PostMapping("/add")
    public void saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    // /api/author?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteAuthor(@RequestParam Integer id)
            throws AuthorNotFoundException {
        authorService.deleteAuthor(id);
        return build(NO_CONTENT, AUTHOR_DELETED_SUCCESSFUL);
    }

    @PutMapping("/update")
    public void updateAuthor(
            @RequestParam("id") Integer id,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email) {

        authorService.updateAuthor(id, country, zipcode, firstName, lastName, street, city, email);
    }
}
