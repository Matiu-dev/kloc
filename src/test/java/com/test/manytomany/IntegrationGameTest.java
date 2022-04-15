package com.test.manytomany;

import com.test.manytomany.model.OutOfTime;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerRole;
import com.test.manytomany.repository.PlayerRepository;
import com.test.manytomany.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@RunWith(SpringRunner.class)
public class IntegrationGameTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerRepository playerService;

    @Autowired
    private GameService gameService;

    @Test
    public void outOfTimeTest() {
        //given
        Player player = new Player();
        player.setLogin("test1");
        player.setPassword("pass");
        player.setPlayerRole(PlayerRole.ROLE_USER);

        entityManager.persist(player);
        entityManager.flush();

        Player playerTwo = new Player();
        playerTwo.setLogin("test2");
        playerTwo.setPassword("pass");
        playerTwo.setPlayerRole(PlayerRole.ROLE_USER);

        entityManager.persist(playerTwo);
        entityManager.flush();

        Player playerThree = new Player();
        playerThree.setLogin("test3");
        playerThree.setPassword("pass");
        playerThree.setPlayerRole(PlayerRole.ROLE_USER);

        entityManager.persist(playerThree);
        entityManager.flush();

        Player playerFour = new Player();
        playerFour.setLogin("test4");
        playerFour.setPassword("pass");
        playerFour.setPlayerRole(PlayerRole.ROLE_USER);

        entityManager.persist(playerFour);
        entityManager.flush();

        Game game = new Game();
        game.setGameTime("1");
        game.setAdditionalTime("0");

        entityManager.persist(game);
        entityManager.flush();

        Board boardFirst = new Board();
        boardFirst.setGame(game);
        boardFirst.addPlayer(player, Color.BLACK, Team.A);
        boardFirst.addPlayer(playerTwo, Color.WHITE, Team.B);

        entityManager.persist(boardFirst);
        entityManager.flush();

        Board boardSecond = new Board();
        boardSecond.setGame(game);
        boardSecond.addPlayer(playerThree, Color.WHITE, Team.A);
        boardSecond.addPlayer(playerFour, Color.BLACK, Team.B);

        entityManager.persist(boardSecond);
        entityManager.flush();


        OutOfTime outOfTime = new OutOfTime();
        outOfTime.setTeam(Team.A);
        outOfTime.setGameId(game.getId());

        //when
        Game gameResponse = gameService.updateGameWinners(outOfTime);


    }
}
