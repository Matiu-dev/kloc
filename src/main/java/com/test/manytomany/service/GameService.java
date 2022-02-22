package com.test.manytomany.service;

import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.PlayerBoardId;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.game.WinnerTeam;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void updateGameWinners(GamePlay gamePlay) {

        Game game = gameRepository.findByid(Long.valueOf(gamePlay.getGameId()));

        if(gamePlay.getTeam().equals(Team.A)) {
            game.setWinnerTeam(WinnerTeam.A);
        }

        if(gamePlay.getTeam().equals(Team.B)) {
            game.setWinnerTeam(WinnerTeam.B);
        }

        createGame(game);
    }

    public Game findGameById(Long gameId) {
        return gameRepository.findByid(gameId);
    }
}
