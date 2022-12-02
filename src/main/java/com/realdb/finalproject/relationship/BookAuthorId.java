package com.realdb.finalproject.relationship;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {
    private static final long serialVersionUID = 8346989999437234998L;
    @Column(name = "BOOK_BOOK_ID", nullable = false)
    private Integer bookBookId;

    @Column(name = "AUTHOR_AUTHOR_ID", nullable = false)
    private Integer authorAuthorId;

    public Integer getBookBookId() {
        return bookBookId;
    }

    public void setBookBookId(Integer bookBookId) {
        this.bookBookId = bookBookId;
    }

    public Integer getAuthorAuthorId() {
        return authorAuthorId;
    }

    public void setAuthorAuthorId(Integer authorAuthorId) {
        this.authorAuthorId = authorAuthorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookAuthorId entity = (BookAuthorId) o;
        return Objects.equals(this.authorAuthorId, entity.authorAuthorId) &&
                Objects.equals(this.bookBookId, entity.bookBookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorAuthorId, bookBookId);
    }

}