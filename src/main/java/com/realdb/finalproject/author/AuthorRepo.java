package com.realdb.finalproject.author;

import com.realdb.finalproject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/2
 */
@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByAemail(String email);
}
