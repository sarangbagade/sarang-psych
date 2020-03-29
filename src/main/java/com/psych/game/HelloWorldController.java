package com.psych.game;

import com.psych.game.model.*;
import com.psych.game.repositories.GameRepository;
import com.psych.game.repositories.PlayerRepository;
import com.psych.game.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dev_test")
public class HelloWorldController {

    //this autowired annotation will create object of class with implements PlayerRepository interface
    //in the constructor
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String hello()
    {
        return "Hello world";
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable(name = "id") Long id)
    {
        return questionRepository.findById(id).orElseThrow();
    }

    @GetMapping("/populate")
    public String populateDB()
    {
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        questionRepository.deleteAll();

        Player p1 = new Player.PlayerBuilder().email("abc@gmail.com").alias("sarang").saltedHashedPassword("abc").build();
        playerRepository.save(p1);
        Player p2 = new Player.PlayerBuilder().email("xyz@gmail.com").alias("priyanka").saltedHashedPassword("abc").build();
        playerRepository.save(p2);

        Set<Player> tempSet = new HashSet<Player>();
        tempSet.add(p1);
        Game game = new Game.Builder().gameMode(GameMode.IS_THIS_A_FACT).leader(p1).players(tempSet).build();
        game.setGameStatus(GameStatus.WAITING_FOR_READY);
        gameRepository.save(game);

        questionRepository.save(new Question("what is the name of india's PM?","narendra modi", GameMode.IS_THIS_A_FACT));

        return "populated";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers()
    {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable(name = "id") Long id)
    {
        return playerRepository.findById(id).orElseThrow();
    }

    @GetMapping("/games")
    public List<Game> getAllGames()
    {
        return gameRepository.findAll();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable(name = "id") Long id)
    {
        return gameRepository.findById(id).orElseThrow();
    }
}
