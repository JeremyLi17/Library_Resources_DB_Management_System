package com.realdb.finalproject.book;

import com.realdb.finalproject.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    // GET ALL
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    // GET ONE
    public Optional<Book> getBook(Integer bookId) {
        return bookRepo.findById(bookId);
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
    @Transactional
    public Book updateBook(Integer bookId, String bookName, String bookTopic) {


        Book book = bookRepo.findById(bookId).orElseThrow(() -> new IllegalStateException(
                "Book with id " + bookId + " does not exist"
        ));

        if (bookName != null && !bookName.equals(book.getBookName())) {
            book.setBookName(bookName);
        }

        if (bookTopic != null && !bookTopic.equals(book.getBookTopic())) {
            book.setBookTopic(bookTopic);
        }

        return bookRepo.save(book);
    }
}
