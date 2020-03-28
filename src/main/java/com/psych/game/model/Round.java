package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Entity
@Table(name = "rounds")
public class Round extends Auditable {

    @ManyToOne
    @Getter @Setter
    private Game game;

    @ManyToOne
    private Question question;

    //submitted answers
    //if the round is deleted then players' submitted answers should be deleted
    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    private Map<Player, PlayerAnswer> playerAnswers = new HashMap<>();

    //if the round is deleted then players' selected answers should be deleted
    @ManyToMany(cascade = CascadeType.ALL)
    @Getter @Setter
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @NotNull
    @Getter @Setter
    private int roundNumber;

    @ManyToOne
    @Getter @Setter
    private EllenAnswer ellenAnswer;
}
