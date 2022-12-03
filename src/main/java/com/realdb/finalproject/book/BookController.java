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
    public Book getBook(@RequestParam Integer id) {
        return bookService.getBook(id);
    }

    @PostMapping()
    public Book createBooks(@RequestParam(required = true) String bookName,
                            @RequestParam(required = true) String bookTopic) {

        return bookService.createBook(bookName, bookTopic);
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
