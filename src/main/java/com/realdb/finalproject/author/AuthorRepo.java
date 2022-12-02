package com.realdb.finalproject.author;

import com.realdb.finalproject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jeremy on 2022/12/2
 */
public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
