package com.realdb.finalproject.relation.bookAuthor;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {
    private static final long serialVersionUID = 8346989999437234998L;
    @Column(name = "BOOK_ID", nullable = false)
    private Integer bookId;

    @Column(name = "AUTHOR_ID", nullable = false)
    private Integer authorId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookBookId) {
        this.bookId = bookBookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorAuthorId) {
        this.authorId = authorAuthorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookAuthorId entity = (BookAuthorId) o;
        return Objects.equals(this.authorId, entity.authorId) &&
                Objects.equals(this.bookId, entity.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }

}