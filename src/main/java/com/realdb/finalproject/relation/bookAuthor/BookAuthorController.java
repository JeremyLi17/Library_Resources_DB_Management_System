package com.realdb.finalproject.relation.bookAuthor;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.book.Book;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.exception.domain.BookNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/bookAuthor")
public class BookAuthorController {
    public static final String BOOK_AUTHOR_DELETED_SUCCESSFULLY = "Book Author deleted successfully";
    private final BookAuthorService bookAuthorService;

    @Autowired
    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping("/list/book/{firstName}-{lastName}")
    public ResponseEntity<List<Book>> getBookByAuthorName(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) throws AuthorNotFoundException {
        List<Book> books = bookAuthorService.getBookByAuthorName(firstName, lastName);
        return new ResponseEntity<>(books, OK);
    }

    @GetMapping("/list/author/{bookName}")
    public ResponseEntity<List<Author>> getAuthorByBookName(
            @PathVariable("bookName") String bookName) throws BookNotFoundException {
        List<Author> authors = bookAuthorService.getAuthorByBookName(bookName);
        return new ResponseEntity<>(authors, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BookAuthor> addBookAuthor(
            @RequestBody BookAuthor bookAuthor)
            throws AuthorNotFoundException, BookNotFoundException {
        bookAuthorService.addBookAuthor(
                bookAuthor.getAuthor().getFirstName(),
                bookAuthor.getAuthor().getLastName(),
                bookAuthor.getBook().getBookName()
        );
        return new ResponseEntity<>(bookAuthor, CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteBookAuthor(
            @RequestParam("id") BookAuthorId bookAuthorId) {
        bookAuthorService.deleteBookAuthor(bookAuthorId);
        return BuildResponse.build(NO_CONTENT, BOOK_AUTHOR_DELETED_SUCCESSFULLY);
    }
}
