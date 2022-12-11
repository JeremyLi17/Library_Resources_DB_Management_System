package com.realdb.finalproject.entity;

import com.realdb.finalproject.entity.book.Book;

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
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "COPY_ID", nullable = false)
    private Integer id;

    @Column(name = "COPY_STATUS", nullable = false, length = 1)
    private String copyStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    public Copy() {}

    public Copy(String copyStatus, Book book) {
        this.copyStatus = copyStatus;
        this.book = book;
    }

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}