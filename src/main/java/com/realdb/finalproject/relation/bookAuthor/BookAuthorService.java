package com.realdb.finalproject.relation.bookAuthor;

import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.author.AuthorRepo;
import com.realdb.finalproject.entity.book.Book;
import com.realdb.finalproject.entity.book.BookRepo;
import com.realdb.finalproject.exception.domain.AuthorNotFoundException;
import com.realdb.finalproject.exception.domain.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class BookAuthorService {
    private final BookAuthorRepo bookAuthorRepo;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    @Autowired
    public BookAuthorService(BookAuthorRepo bookAuthorRepo,
                             BookRepo bookRepo,
                             AuthorRepo authorRepo) {
        this.bookAuthorRepo = bookAuthorRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public List<Book> getBookByAuthorName(String firstName, String lastName)
            throws AuthorNotFoundException {
        Optional<Author> authorOpt = authorRepo.findAuthorByLastNameAndFirstName(lastName, firstName);
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException("Author: " + lastName + " " + firstName + " not found");
        }
        Author author = authorOpt.get();
        List<Book> books = bookAuthorRepo.getBookByAuthorId(author.getId());
        return books;
    }

    public List<Author> getAuthorByBookName(String bookName) throws BookNotFoundException {
        Optional<Book> bookOpt = bookRepo.findBookByBookName(bookName);
        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException("Book: " + bookName + " not found");
        }
        Book book = bookOpt.get();
        List<Author> authors = bookAuthorRepo.getAuthorByBookId(book.getId());
        return authors;
    }

    public BookAuthor addBookAuthor(String authorFirstName, String authorLastName, String bookName)
            throws BookNotFoundException, AuthorNotFoundException {
        Optional<Book> bookOpt = bookRepo.findBookByBookName(bookName);
        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException("Book: " + bookName + " not found");
        }
        Optional<Author> authorOpt = authorRepo.findAuthorByLastNameAndFirstName(
                authorLastName, authorFirstName
        );
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException(
                    "Author: " + authorFirstName + " " + authorLastName + " not found"
            );
        }
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setAuthor(authorOpt.get());
        bookAuthor.setBook(bookOpt.get());
        bookAuthorRepo.save(bookAuthor);
        return bookAuthor;
    }

    public void deleteBookAuthor(BookAuthorId bookAuthorId) {
        bookAuthorRepo.deleteById(bookAuthorId);
    }
}
