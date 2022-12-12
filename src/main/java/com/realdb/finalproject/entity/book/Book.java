package com.realdb.finalproject.entity.book;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "BOOK_SEQUENCE"
    )
    @SequenceGenerator(
            name = "BOOK_SEQUENCE",
            sequenceName = "BOOK_SEQUENCE",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "BOOK_ID", nullable = false)
    private Integer id;

    @Column(name = "BOOK_NAME", nullable = false, length = 100)
    private String bookName;

    @Column(name = "BOOK_TOPIC", nullable = false, length = 30)
    private String bookTopic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTopic() {
        return bookTopic;
    }

    public void setBookTopic(String bookTopic) {
        this.bookTopic = bookTopic;
    }

}