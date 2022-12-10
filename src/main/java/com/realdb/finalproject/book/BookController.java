package com.realdb.finalproject.book;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping()
    public Optional<Book> getBook(@RequestParam Integer id) {
        return bookService.getBook(id);
    }

    @PostMapping()
    public Book createBooks(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping()
    public Book updateBook(@RequestParam Integer id,
                           @RequestParam(required = false) String bookName,
                           @RequestParam(required = false) String bookTopic) {

        return bookService.updateBook(id, bookName, bookTopic);
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
    }
}
