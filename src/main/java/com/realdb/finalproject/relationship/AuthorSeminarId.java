package com.realdb.finalproject.relationship;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AuthorSeminarId implements Serializable {
    private static final long serialVersionUID = 3496926304351414381L;
    @Column(name = "AUTHOR_AUTHOR_ID", nullable = false)
    private Integer authorAuthorId;

    @Column(name = "SEMINAR_EVENT_ID", nullable = false)
    private Integer seminarEventId;

    public Integer getAuthorAuthorId() {
        return authorAuthorId;
    }

    public void setAuthorAuthorId(Integer authorAuthorId) {
        this.authorAuthorId = authorAuthorId;
    }

    public Integer getSeminarEventId() {
        return seminarEventId;
    }

    public void setSeminarEventId(Integer seminarEventId) {
        this.seminarEventId = seminarEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthorSeminarId entity = (AuthorSeminarId) o;
        return Objects.equals(this.seminarEventId, entity.seminarEventId) &&
                Objects.equals(this.authorAuthorId, entity.authorAuthorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seminarEventId, authorAuthorId);
    }

}