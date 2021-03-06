package com.test.manytomany.repository;

import com.test.manytomany.model.board.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BoardRepository extends CrudRepository<Board, Long> {
    Board findByid(UUID boardId);
}
