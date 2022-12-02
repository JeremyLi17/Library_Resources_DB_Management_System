package com.realdb.finalproject.author;

import com.realdb.finalproject.entity.Author;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jeremy on 2022/12/2
 */
@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }
}
