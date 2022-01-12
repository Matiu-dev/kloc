package com.test.manytomany.service;

import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public Game findGameById(Long gameId) {
        return gameRepository.findByid(gameId);
    }
}
