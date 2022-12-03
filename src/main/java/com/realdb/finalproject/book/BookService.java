package com.realdb.finalproject.book;

import com.realdb.finalproject.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    // GET ALL
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    // GET ONE
    public Book getBook(Integer bookId) {
        return bookRepo.findById(bookId).isEmpty() ? null : bookRepo.findById(bookId).get();
    }

    // CREATE
    public Book createBook(String bookName, String bookTopic) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setBookTopic(bookTopic);

        return bookRepo.save(book);
    }

    // DELETE
    public void deleteBook(Integer bookId) {
        bookRepo.deleteById(bookId);
    }

    // UPDATE
    public Book updateBook(Integer bookId, String bookName, String bookTopic) {
        if (bookRepo.findById(bookId).isEmpty()) {
            return null;
        }

        Book book = bookRepo.findById(bookId).get();
        if (bookName != null && !bookName.equals(book.getBookName())) {
            book.setBookName(bookName);
        }

        if (bookTopic != null && !bookTopic.equals(book.getBookTopic())) {
            book.setBookTopic(bookTopic);
        }

        return bookRepo.save(book);
    }
}
