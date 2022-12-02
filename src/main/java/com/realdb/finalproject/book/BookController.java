package com.realdb.finalproject.book;

import com.realdb.finalproject.entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Book createBooks(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Integer id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    @GetMapping("/{bookId}")
    public void deleteBook(@PathVariable(value = "bookId") Integer id) {
        bookService.deleteBook(id);
    }
}
