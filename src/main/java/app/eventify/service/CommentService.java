package app.eventify.service;

import app.eventify.model.Comment;

import java.util.List;

public interface CommentService {
    /* Ja koristime za da gi najdeme site komentari shto postojat */
    List<Comment> findAll();

    /* Ja koristime za da najdeme komentar spored ID vo baza */
    Comment findById(Long id);

    /* Za da se editne veke postoechki komentar */

    Comment editComment(Comment newComment);

    /* Ja koristime za kreiranje nov komenter i.e. CREATE */
    Comment createComment(Comment newComment);

    /* Brishenje na komentar spored ID */
    void deleteById(Long id);

}
