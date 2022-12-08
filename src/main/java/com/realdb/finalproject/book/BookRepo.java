package com.realdb.finalproject.book;
import com.realdb.finalproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepo extends JpaRepository<Book, Integer>{
}
