package com.psych.game.repositories;

import com.psych.game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//this tells JPA to create an interface to interact with the player table
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
