package com.test.manytomany.repository;

import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByid(Long gameId);


}
