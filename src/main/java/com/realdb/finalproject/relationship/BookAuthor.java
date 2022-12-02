package com.realdb.finalproject.relationship;

import com.realdb.finalproject.entity.Author;
import com.realdb.finalproject.entity.Book;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_AUTHOR")
public class BookAuthor {
    @EmbeddedId
    private BookAuthorId id;

    @MapsId("bookBookId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_BOOK_ID", nullable = false)
    private Book bookBook;

    @MapsId("authorAuthorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_AUTHOR_ID", nullable = false)
    private Author authorAuthor;

    public BookAuthorId getId() {
        return id;
    }

    public void setId(BookAuthorId id) {
        this.id = id;
    }

    public Book getBookBook() {
        return bookBook;
    }

    public void setBookBook(Book bookBook) {
        this.bookBook = bookBook;
    }

    public Author getAuthorAuthor() {
        return authorAuthor;
    }

    public void setAuthorAuthor(Author authorAuthor) {
        this.authorAuthor = authorAuthor;
    }

}