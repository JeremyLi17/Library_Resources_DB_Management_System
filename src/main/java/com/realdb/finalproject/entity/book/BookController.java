package com.realdb.finalproject.entity.book;

import com.realdb.finalproject.exception.domain.BookNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/find/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable String bookName)
            throws BookNotFoundException {

        Book book = bookService.getBookByName(bookName);
        return new ResponseEntity<>(book, OK);
    }

    @PostMapping("/add")
    public Book createBooks(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("update")
    public Book updateBook(@RequestParam String currentBookName,
                           @RequestParam(required = false) String bookName,
                           @RequestParam(required = false) String bookTopic)
            throws BookNotFoundException {
        return bookService.updateBook(currentBookName, bookName, bookTopic);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
    }
}
