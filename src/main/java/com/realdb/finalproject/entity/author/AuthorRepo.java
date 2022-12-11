package com.realdb.finalproject.entity.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/2
 */
@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByEmail(String email);
}
