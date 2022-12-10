package com.realdb.finalproject.relationship;

import com.realdb.finalproject.author.Author;
import com.realdb.finalproject.entity.Seminar;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR_SEMINAR")
public class AuthorSeminar {
    @EmbeddedId
    private AuthorSeminarId id;

    @MapsId("authorAuthorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_AUTHOR_ID", nullable = false)
    private Author authorAuthor;

    @MapsId("seminarEventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEMINAR_EVENT_ID", nullable = false)
    private Seminar seminarEvent;

    @Column(name = "IVITATION_ID", nullable = false)
    private Integer ivitationId;

    public AuthorSeminarId getId() {
        return id;
    }

    public void setId(AuthorSeminarId id) {
        this.id = id;
    }

    public Author getAuthorAuthor() {
        return authorAuthor;
    }

    public void setAuthorAuthor(Author authorAuthor) {
        this.authorAuthor = authorAuthor;
    }

    public Seminar getSeminarEvent() {
        return seminarEvent;
    }

    public void setSeminarEvent(Seminar seminarEvent) {
        this.seminarEvent = seminarEvent;
    }

    public Integer getIvitationId() {
        return ivitationId;
    }

    public void setIvitationId(Integer ivitationId) {
        this.ivitationId = ivitationId;
    }

}