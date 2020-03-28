package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable {

    @ManyToMany
    @Getter
    @Setter
    private Set<Player> players;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameMode gameMode;

    //better to have list of rounds instead of set of rounds as order matters
    //one game can have many rounds
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    @Getter @Setter
    private Boolean hasEllen = false;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private Player leader;
    //multiple games can have one particular leader

    //if the game is deleted then player stats should delete
    @ManyToMany(cascade = CascadeType.ALL)
    private Map<Player, Stat> playerStats = new HashMap<>();
    //this will a table called game_player_stats
    //this will create a table with game_id, player_id, stat_id
    //this table will contain many games, many players and many stats

    @NotNull
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @ManyToMany
    @Getter @Setter
    private Set<Player> readyPlayers = new HashSet<>();

    @Getter @Setter
    private int numRounds = 10;
}
