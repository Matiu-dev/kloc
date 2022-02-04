package com.test.manytomany.service;

import com.test.manytomany.chesspiecerules.*;
import com.test.manytomany.model.connect.ConnectRequest;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
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

    public ConnectResponse createAndAddPlayerToBoard(Player player) {

        //response
        ConnectResponse connectResponse = new ConnectResponse();

        //?
        Color color;

        //tworzy i zapisuje gre
        Game game = new Game();
        gameService.createGame(game);

        //tworzy , dodaje gracza i zapisuje plansze
        Board boardFirst = new Board();
        boardFirst.setGame(game);

        Random random = new Random();
        if(random.nextInt(2) == 0) {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK);
            color = Color.BLACK;
            connectResponse.setPlayerIdMove(-1L);
        }else {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE);
            color = Color.WHITE;
            connectResponse.setPlayerIdMove(player.getId());
        }

        //tworzenie drugiej planszy
        Board boardSecond = new Board();
        boardSecond.setGame(game);

        boardRepository.save(boardFirst);
        boardRepository.save(boardSecond);

        connectResponse.setBoardId(boardFirst.getId());
        connectResponse.setGameId(game.getId());
        connectResponse.setBoardIdAdditional(boardSecond.getId());
        connectResponse.setColor(color);
        connectResponse.setPlayerId(player.getId());

        return connectResponse;
    }

    public ConnectResponse connectToGame(ConnectRequest request) {

        ConnectResponse connectResponse = new ConnectResponse();

        Game game = gameService.findGameById(request.getGameId());
        Player player = playerService.findPlayerById(request.getPlayerId());

        connectResponse.setGameId(game.getId());
        connectResponse.setPlayerId(player.getId());

        Set<Board> boardList = game.getBoards();

        for(Board b: boardList) {
            if(b.getPlayers().size() == 1) {
                //sprawdzanie koloru gracza na szachownicy
                connectResponse.setBoardId(b.getId());
                b.getPlayers().stream().forEach(v -> {
                    if(v.getColor().equals(Color.WHITE)){
                        b.addPlayer(player, Color.BLACK);
                        connectResponse.setColor(Color.BLACK);
                    }else if(v.getColor().equals(Color.BLACK)){
                        b.addPlayer(player, Color.WHITE);
                        connectResponse.setColor(Color.WHITE);
                        connectResponse.setPlayerIdMove(player.getId());
                    }
                });

                boardRepository.save(b);
               // return new ConnectResponse(game.getId(), b.getId(), 2L, player.getId(), Color.WHITE);
                return connectResponse;
            }
        }

        return connectResponse;

    }

    public GamePlay makeAMove(GamePlay gamePlay) throws Exception {
        //sprawdzanie czy ktos juz wygral przy aktualnym polozeniu i zapis do bazy TODO
        //sprawdzenie czy gamId istnieje TODO

        //zmiana nastepnego koloru ruchu



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

        //bialy goniec

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEBISHOP.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new Bishop().checkMoveWhite(gamePlay);
        }

        //czarny gonec

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKBISHOP.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new Bishop().checkMoveBlack(gamePlay);
        }

        //bialy hetman

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEQUEEN.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new Queen().checkMoveWhite(gamePlay);
        }

        //black hetman

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKQUEEN.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new Queen().checkMoveBlack(gamePlay);
        }

        //bialy skoczek

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEKNIGHT.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new Knight().checkMoveWhite(gamePlay);
        }

        //czarny skoczek

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKNIGHT.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new Knight().checkMoveBlack(gamePlay);
        }

        //biały krol

        if(gamePlay.getFigureNameOld().equals(Pieces.WHITEKING.getPiece())
                && !gamePlay.checkWhite(gamePlay.getFigureNameNew())) {
            return new King().checkMoveWhite(gamePlay);
        }

        //czarny krol

        if(gamePlay.getFigureNameOld().equals(Pieces.BLACKKING.getPiece())
                && !gamePlay.checkBlack(gamePlay.getFigureNameNew())) {
            return new King().checkMoveBlack(gamePlay);
        }

        //roszada

        //bicie w przelocie

        //szach

        //szach mat - zapis

        //awans

        gamePlay.setMoveStatus(MoveStatus.BAD);
        return gamePlay;
    }
}