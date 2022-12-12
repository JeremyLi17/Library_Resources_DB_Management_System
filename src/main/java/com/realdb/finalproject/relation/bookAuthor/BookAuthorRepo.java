package com.realdb.finalproject.relation.bookAuthor;

import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jeremy on 2022/12/11
 */
@Repository
public interface BookAuthorRepo extends JpaRepository<BookAuthor, BookAuthorId> {
    @Query("SELECT b FROM Book b WHERE b.id IN " +
            "(SELECT ba.book.id FROM BookAuthor ba WHERE ba.author.id = ?1)")
    List<Book> getBookByAuthorId(Integer id);

    @Query("SELECT a FROM Author a WHERE a.id IN " +
            "(SELECT ba.author.id FROM BookAuthor ba WHERE ba.book.id = ?1)")
    List<Author> getAuthorByBookId(Integer id);
}
