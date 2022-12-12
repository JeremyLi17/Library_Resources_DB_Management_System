package com.realdb.finalproject.relation.authorSeminar;

import com.realdb.finalproject.entity.author.Author;
import com.realdb.finalproject.entity.event.Seminar;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR_SEMINAR")
public class AuthorSeminar {
    @EmbeddedId
    private AuthorSeminarId id;

    @MapsId("authorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;

    @MapsId("seminarEventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEMINAR_EVENT_ID", nullable = false)
    private Seminar seminarEvent;

    @Column(name = "INVITATION_ID", nullable = false)
    private Integer invitationId;

    public AuthorSeminarId getId() {
        return id;
    }

    public void setId(AuthorSeminarId id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Seminar getSeminarEvent() {
        return seminarEvent;
    }

    public void setSeminarEvent(Seminar seminarEvent) {
        this.seminarEvent = seminarEvent;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

}