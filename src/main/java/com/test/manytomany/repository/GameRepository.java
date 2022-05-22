package com.test.manytomany.repository;

import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findById(UUID gameId);

    List<Game> findAll();


}
