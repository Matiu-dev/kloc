package com.test.manytomany.service;

import com.test.manytomany.chesspiecerules.Pawn;
import com.test.manytomany.chesspiecerules.Rook;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.ConnectRequest;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Service
@Slf4j
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

    @Autowired
    private GameService gameService;

    public Board saveBoard(Board board) {

        Game game = new Game();
        gameService.createGame(game);

        Board nBoard = new Board();
        nBoard.setGame(game);
        board.setTest(board.getTest());

        board.getPlayers().stream().forEach(v -> {
            nBoard.addPlayer(playerService.findPlayerById(v.getId()));

        });
        boardRepository.save(nBoard);


         return nBoard;
    }

    public ConnectRequest connectToBoard(ConnectRequest request) throws InvalidParamException, InvalidGameException {

        Board nBoard = findBoardById(request.getBoardId());

        //sprawdzanie czy do boarda przypisanych jest 2 graczy

        if(nBoard.getPlayers().size()<2) {
            nBoard.addPlayer(playerService.findPlayerById(request.getPlayerId()));
            boardRepository.save(nBoard);
        }else {
            log.info("Board has 2 players");
        }

        return request;
    }

    public GamePlay makeAMove(GamePlay gamePlay) throws Exception {
        //sprawdzanie czy ktos juz wygral przy aktualnym polozeniu i zapis do bazy
        //----ruchy----------
        //ruch piona i ruch piona z biciem

        //biały pionek
        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEPAWN.getPiece())
        && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new Pawn().checkMoveWhite(gamePlay);
        }

        //czarny pionek
        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKPAWN.getPiece())
        && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new Pawn().checkMoveBlack(gamePlay);
        }

        //biała wieża

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEROOK.getPiece())
        && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new Rook().checkMoveWhite(gamePlay);
        }

        //czarna wieza

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKROOK.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new Rook().checkMoveBlack(gamePlay);
        }

        //ruch hetmana
        //ruch wiezy
        //ruch skoczka
        //ruch

        //roszada

        //bicie w przelocie

        //szach

        //szach mat

        //awans

        gamePlay.setMoveStatus(MoveStatus.BAD);
        return gamePlay;
    }
}