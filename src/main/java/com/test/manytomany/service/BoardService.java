package com.test.manytomany.service;

import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.Board;
import com.test.manytomany.model.ConnectRequest;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.Player;
import com.test.manytomany.repository.BoardRepository;
import com.test.manytomany.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board findBoardById(Long boardId) {
        return boardRepository.findByid(boardId);
    }

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    public Board saveBoard(Board board) {

        Board nBoard = new Board();
        board.setTest(board.getTest());

        board.getPlayers().stream().forEach(v -> {
            nBoard.addPlayer(playerService.findPlayerById(v.getId()));

        });
        boardRepository.save(nBoard);


         return nBoard;
    }

    public Board connectToBoard(Board board) throws InvalidParamException, InvalidGameException {

        Board nBoard = findBoardById(board.getId());


        board.getPlayers().stream().forEach(v -> {
//            System.out.println(v.getId());
            nBoard.addPlayer(playerService.findPlayerById(v.getId()));

        });
        boardRepository.save(nBoard);

        return nBoard;
    }

    public GamePlay makeAMove(GamePlay gamePlay) {
//        System.out.println("Koordynaty nowe " + gamePlay.getCoordinateNew());
//        System.out.println("Figura nowa " + gamePlay.getFigureNameNew());
//        System.out.println("Koordynaty stare " + gamePlay.getCoordinateOld());
//        System.out.println("Figura stara " + gamePlay.getFigureNameOld());

        //sprawdzanie czy ktos juz wygral przy aktualnym polozeniu i zapis do bazy

        return gamePlay;
    }
}