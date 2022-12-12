package com.realdb.finalproject.entity.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByBookName(String bookName);
}
