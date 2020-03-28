package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class Player extends User implements Serializable {

    @NotBlank
    @Getter
    @Setter
    private String alias;
    @Getter
    @Setter
    private String psychFaceUrl;
    @Getter
    @Setter
    private String picUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter @Setter
    private Stat Stat = new Stat();

    @ManyToMany(mappedBy = "players")
    @Getter @Setter
    private Set<Game> games = new HashSet<>();

    public Player()
    {

    }

    public static final class PlayerBuilder {
        private String alias;
        private String email;
        private String psychFaceUrl;
        private String saltedHashedPassword;
        private String picUrl;

        public PlayerBuilder() {
        }

        public static PlayerBuilder aPlayer() {
            return new PlayerBuilder();
        }

        public PlayerBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public PlayerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PlayerBuilder psychFaceUrl(String psychFaceUrl) {
            this.psychFaceUrl = psychFaceUrl;
            return this;
        }

        public PlayerBuilder saltedHashedPassword(String saltedHashedPassword) {
            this.saltedHashedPassword = saltedHashedPassword;
            return this;
        }

        public PlayerBuilder picUrl(String picUrl) {
            this.picUrl = picUrl;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.setAlias(alias);
            player.setEmail(email);
            player.setPsychFaceUrl(psychFaceUrl);
            player.setSaltedHashedPassword(saltedHashedPassword);
            player.setPicUrl(picUrl);
            return player;
        }
    }
}
