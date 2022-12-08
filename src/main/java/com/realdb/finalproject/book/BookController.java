package com.realdb.finalproject.book;
import com.realdb.finalproject.entity.Book;
import com.realdb.finalproject.entity.Copy;
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
    @PostMapping()
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }
    @DeleteMapping()
    public void deleteBook(@RequestParam Integer id) { bookService.deleteBook(id);}
    @GetMapping("/{id}")
    public Optional<Book> getBookByID(@PathVariable("id") Integer id){
        return bookService.getBookByID(id);
    }






}
