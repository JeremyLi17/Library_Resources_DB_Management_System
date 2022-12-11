package com.realdb.finalproject.entity.book;

import com.realdb.finalproject.exception.domain.BookNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    public Book getBookByName(String name) throws BookNotFoundException {
        Optional<Book> book = bookRepo.findBookByBookName(name);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book not found with book name: " + name);
        }
        return book.get();
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
    public Book updateBook(String currentBookName, String newBookName, String bookTopic)
            throws BookNotFoundException {
        Book book = bookRepo.findBookByBookName(currentBookName).orElseThrow(() -> new BookNotFoundException(
                "Book with name " + currentBookName + " does not exist"
        ));

        if (StringUtils.isNotBlank(newBookName) && !newBookName.equals(book.getBookName())) {
            book.setBookName(newBookName);
        }
        if (StringUtils.isNotBlank(bookTopic) && !bookTopic.equals(book.getBookTopic())) {
            book.setBookTopic(bookTopic);
        }

        return bookRepo.save(book);
    }
}
