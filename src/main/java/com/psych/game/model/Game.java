package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "games")
public class Game extends Auditable {

    @ManyToMany
    @JsonIdentityReference
    @Getter
    @Setter
    private Set<Player> players;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    @JsonIgnore
    private GameMode gameMode;

    //better to have list of rounds instead of set of rounds as order matters
    //one game can have many rounds
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    //as game owns the rounds this is managed reference
    @JsonIgnore
    private List<Round> rounds = new ArrayList<>();

    @Getter @Setter
    private Boolean hasEllen = false;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    //as the game does not own the leader, this is json identity reference
    @JsonIgnore
    private Player leader;
    //multiple games can have one particular leader

    //if the game is deleted then player stats should delete
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Map<Player, Stat> playerStats = new HashMap<>();
    //this will a table called game_player_stats
    //this will create a table with game_id, player_id, stat_id
    //this table will contain many games, many players and many stats

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @ManyToMany
    @Getter @Setter
    @JsonIgnore
    private Set<Player> readyPlayers = new HashSet<>();

    @Getter @Setter
    private int numRounds = 10;

    public Game()
    {

    }


    public static final class Builder {
        private Set<Player> players;
        private GameMode gameMode;
        private Player leader;

        public Builder() {
        }

        public static Builder aGame() {
            return new Builder();
        }

        public Builder players(Set<Player> players) {
            this.players = players;
            return this;
        }

        public Builder gameMode(GameMode gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public Builder leader(Player leader) {
            this.leader = leader;
            return this;
        }

        public Game build() {
            Game game = new Game();
            game.setPlayers(players);
            game.setGameMode(gameMode);
            game.setLeader(leader);
            return game;
        }
    }
}
