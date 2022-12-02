package com.realdb.finalproject.author;

import com.realdb.finalproject.entity.Author;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jeremy on 2022/12/2
 */
@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }
}
