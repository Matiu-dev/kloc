package com.test.manytomany.repository;

import com.test.manytomany.model.board.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
    Board findByid(Long boardId);
}
