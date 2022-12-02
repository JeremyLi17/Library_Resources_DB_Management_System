package com.realdb.finalproject.book;

import com.realdb.finalproject.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;


    // GET
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    // CREATE
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    // DELETE
    public void deleteBook(Integer bookId) {
        bookRepo.deleteById(bookId);
    }

    // UPDATE
    public Book updateBook(Integer bookId, Book bookDetails) {
        if (bookRepo.findById(bookId).isEmpty()) {
            return null;
        }

        Book book = bookRepo.findById(bookId).get();
        book.setBookName(bookDetails.getBookName());
        book.setBookTopic(bookDetails.getBookTopic());
        book.setId(bookDetails.getId());

        return bookRepo.save(book);
    }
}
