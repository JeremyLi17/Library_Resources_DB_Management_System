package com.realdb.finalproject.author;

import com.realdb.finalproject.entity.Author;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/2
 */
@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping()
    public Optional<Author> getAuthor(@RequestParam Integer id) {
        return authorService.getAuthor(id);
    }

    @PostMapping()
    public void saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    // /api/author?id=1
    @DeleteMapping()
    public void deleteAuthor(@RequestParam Integer id) {
        authorService.deleteAuthor(id);
    }

    @PutMapping(path = "{id}")
    public void updateAuthor(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer zipcode,
            @RequestParam(required = false) String fName,
            @RequestParam(required = false) String lName,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String email) {

        authorService.updateAuthor(id, country, zipcode, fName, lName, street, city, email);
    }
}
