package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question extends Auditable {

    @NotNull
    @Getter @Setter
    private String question;
    @NotNull
    @Getter @Setter
    private String correctAnswer;

    public Question()
    {

    }

    //if the question is deleted, then all of its ellen answers should be deleted
    //one question can have many ellen answers
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    //this is because if the question is deleted all of its ellen answers should be deleted
    @JsonManagedReference
    private Set<EllenAnswer> ellenAnswers;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter @Setter
    private GameMode gameMode;

    public Question(@NotNull String question, @NotNull String correctAnswer, @NotNull GameMode gameMode) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.gameMode = gameMode;
    }
}
