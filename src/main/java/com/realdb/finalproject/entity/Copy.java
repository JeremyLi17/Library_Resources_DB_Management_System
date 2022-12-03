package com.realdb.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.realdb.finalproject.entity.Book;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "COPY")
public class Copy {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "COPY_SEQUENCE"
    )
    @SequenceGenerator(
            name = "COPY_SEQUENCE",
            sequenceName = "COPY_SEQUENCE",
            initialValue = 15,
            allocationSize = 1)
    @Column(name = "COPY_ID", nullable = false)
    private Integer id;

    @Column(name = "COPY_STATUS", nullable = false, length = 1)
    private String copyStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_BOOK_ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book bookBook;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Book getBookBook() {
        return bookBook;
    }

    public void setBookBook(Book bookBook) {
        this.bookBook = bookBook;
    }

}