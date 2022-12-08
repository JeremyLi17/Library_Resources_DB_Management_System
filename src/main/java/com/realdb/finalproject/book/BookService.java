package com.realdb.finalproject.book;
import com.realdb.finalproject.entity.Copy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.realdb.finalproject.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class BookService {
    private final BookRepo bookRepo;
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }
    public void addBook(Book book){
        bookRepo.save(book);
    }
    public void deleteBook(Integer id) { bookRepo.deleteById(id);}
    public Optional<Book> getBookByID(Integer id){
        return bookRepo.findById(id);

    }
}
