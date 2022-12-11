package com.realdb.finalproject.entity.author;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.realdb.finalproject.utility.BuildResponse.build;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author jeremy on 2022/12/2
 */
@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {

    public static final String AUTHOR_DELETED_SUCCESSFUL = "Author deleted successful";

    private final AuthorService authorService;

    @GetMapping("/getAll")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/get")
    public Optional<Author> getAuthorByName(@RequestParam("lastName") String lastName,
                                            @RequestParam("firstName") String firstName) {
        return authorService.getAuthor(lastName, firstName);
    }

    @PostMapping("/add")
    public void saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    // /api/author?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteAuthor(@RequestParam Integer id) throws AuthorNotFoundException {
        authorService.deleteAuthor(id);
        return build(NO_CONTENT, AUTHOR_DELETED_SUCCESSFUL);
    }

    @PutMapping("/update")
    public void updateAuthor(
            @RequestParam("id") Integer id,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) String fName,
            @RequestParam(required = false) String lName,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email) {

        authorService.updateAuthor(id, country, zipcode, fName, lName, street, city, email);
    }
}
