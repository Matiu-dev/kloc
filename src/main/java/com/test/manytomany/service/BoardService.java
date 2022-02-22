package com.test.manytomany.service;

import com.test.manytomany.chesspiecerules.*;
import com.test.manytomany.model.*;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.connect.ConnectRequest;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Team team;

        //tworzy i zapisuje gre
        Game game = new Game();
        gameService.createGame(game);

        //tworzy , dodaje gracza i zapisuje plansze
        Board boardFirst = new Board();
        boardFirst.setGame(game);


        Random random = new Random();
        if(random.nextInt(2) == 0) {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK, Team.B);
            color = Color.BLACK;
            team = Team.B;
            connectResponse.setPlayerIdMove(-1L);
        }else {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE, Team.A);
            color = Color.WHITE;
            team = Team.A;
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
        connectResponse.setTeam(team);
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

        List<Board> newListBoard = new ArrayList<>();
        for(Board board : boardList){
            newListBoard.add(board);
        }

        for(Board b: boardList) {
            if(b.getPlayers().size() == 1) {//1 gracz
                //sprawdzanie koloru gracza na szachownicy
                connectResponse.setBoardId(b.getId());
                b.getPlayers().stream().forEach(v -> {
                    if(v.getColor().equals(Color.WHITE)){
                        if(v.getTeam().equals(Team.A)){
                            b.addPlayer(player, Color.BLACK, Team.B);
                            connectResponse.setColor(Color.BLACK);
                            connectResponse.setTeam(Team.B);
                        }

                        if(v.getTeam().equals(Team.B)){
                            b.addPlayer(player, Color.BLACK, Team.A);
                            connectResponse.setColor(Color.BLACK);
                            connectResponse.setTeam(Team.A);
                        }

                    }else if(v.getColor().equals(Color.BLACK)){
                        if(v.getTeam().equals(Team.A)){
                            b.addPlayer(player, Color.WHITE, Team.B);
                            connectResponse.setColor(Color.WHITE);
                            connectResponse.setTeam(Team.B);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                        if(v.getTeam().equals(Team.B)){
                            b.addPlayer(player, Color.WHITE, Team.A);
                            connectResponse.setColor(Color.WHITE);
                            connectResponse.setTeam(Team.A);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                    }
                });

                boardRepository.save(b);
               // return new ConnectResponse(game.getId(), b.getId(), 2L, player.getId(), Color.WHITE);

                //ustawienie drugiej szachownicy bo
                //w pierwszej jest wolne miejsce
                if(connectResponse.getBoardIdAdditional() == null){
                    connectResponse.setBoardIdAdditional(newListBoard.get(1).getId());
                }

                return connectResponse;
            }

            if(b.getPlayers().size() == 0) { // 0 graczy
                System.out.println("proba dolaczenia do bordu: " + b.getId());

                connectResponse.setBoardId(b.getId());

                Random random = new Random();
                if(random.nextInt(2) == 0) {
                    b.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK, Team.A);
                    connectResponse.setColor(Color.BLACK);
                }else {
                    b.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE, Team.B);
                    connectResponse.setColor(Color.WHITE);
                }

                boardRepository.save(b);

                //ustawienie drugiej szachownicy bo
                //w pierwszej jest wolne miejsce
                if(connectResponse.getBoardIdAdditional()== null){
                    System.out.println("nie");
                    connectResponse.setBoardIdAdditional(newListBoard.get(1).getId());
                }

                return connectResponse;
            }

            connectResponse.setBoardIdAdditional(b.getId());//ustawienie dodatkowego boardu id
        }

        return connectResponse;

    }

    public GamePlay makeAMove(GamePlay gamePlay) throws Exception {
        //sprawdzanie czy ktos juz wygral przy aktualnym polozeniu i zapis do bazy TODO
        //sprawdzenie czy gamId istnieje TODO

        //zmiana nastepnego koloru ruchu
        //sprawdzic czy jestes szachowany


        boolean isKing = false;

        if(gamePlay.getFigureNameNew().equals(Pieces.BLACKKING.getPiece())
        || gamePlay.getFigureNameNew().equals(Pieces.WHITEKING.getPiece())){
            isKing = true;
        }

        //wszystkie ruchy
        MainRules mainRules = new MainRules();
        gamePlay = mainRules.checkRules(gamePlay);


        //bicie w przelocie - dla pionka

        //awans

        //pat - remis

        //szach TODO

        //sprawdzanie czy ktos juz wygral czyli  mat TODO

        //sprawdzic czy po ruchu ktorys pionek moze zbic krola
        //zmiana game resultu


        //tylko zbicie króla - bez zbednych dodatków
        if(isKing && gamePlay.getMoveStatus().equals(MoveStatus.OK) && !gamePlay.isCastlingMove()) {
            gamePlay.setGameResult(GameResult.CHECKMATE);
        }else {
            gamePlay.setGameResult(GameResult.EMPTY);
        }

        return gamePlay;
    }
}