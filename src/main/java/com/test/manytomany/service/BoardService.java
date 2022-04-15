package com.test.manytomany.service;

import com.test.manytomany.chesspiecerules.*;
import com.test.manytomany.model.*;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.connect.*;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.board.Board;
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

    public Board findBoardById(UUID boardId) {
        return boardRepository.findByid(boardId);
    }

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ChatService chatService;

    public ConnectResponse createAndAddPlayerToBoard(CreateGameRequest createGameRequest, Player player) {
//        System.out.println(createGameRequest.getPlayerId());

//        Player player = playerService.findPlayerByLogin(createGameRequest.getLogin());

        //response
        ConnectResponse connectResponse = new ConnectResponse();

        //?
        Color color; // kolor na 1 szachownicy
        Color colorSecond = null; // kolor na 2 szachownicy
        Team team;

        //tworzy i zapisuje gre
        Game game = new Game();
        game.setGameType(createGameRequest.getGameType());
        game.setGameTime(createGameRequest.getGameTime());
        game.setAdditionalTime(createGameRequest.getAdditionalTime());
        gameService.createGame(game);

        //tworzy , dodaje gracza i zapisuje plansze
        Board boardFirst = new Board();
        boardFirst.setGame(game);

        //tworzenie drugiej planszy
        Board boardSecond = new Board();
        boardSecond.setGame(game);

//        createGameRequest.getGameType();

        Random random = new Random();
        if (random.nextInt(2) == 0) {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK, Team.B);
            color = Color.BLACK;
            team = Team.B;
            connectResponse.setPlayerIdMove(null);

            if (createGameRequest.getGameType().equals("2")) {
                boardSecond.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE, Team.B);
                colorSecond = Color.WHITE;
            }
        } else {
            boardFirst.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE, Team.A);
            color = Color.WHITE;
            team = Team.A;
            connectResponse.setPlayerIdMove(player.getId());

            if (createGameRequest.getGameType().equals("2")) {
                boardSecond.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK, Team.A);
                colorSecond = Color.BLACK;
            }
        }

        boardRepository.save(boardFirst);
        boardRepository.save(boardSecond);

        connectResponse.setBoardId(boardFirst.getId());
        connectResponse.setBoardIdAdditional(boardSecond.getId());
        connectResponse.setGameId(game.getId());
        connectResponse.setColor(color);
        connectResponse.setColorSecond(colorSecond);
        connectResponse.setTeam(team);
        connectResponse.setPlayerId(player.getId());

        //laczenie z chatem
        connectResponse.setChatId(chatService.createAndAddPlayerToChat(
                        new ChatConnectRequest(playerService.findPlayerById(player.getId()), connectResponse.getGameId()))
                .getChatId());

        return connectResponse;
    }

    public ConnectResponse connectToGame(ConnectRequest request) {

        Player player = playerService.findPlayerByLogin(request.getLogin());

        ConnectResponse connectResponse = new ConnectResponse();

        Game game = gameService.findGameById(request.getGameId());
//        Player player = playerService.findPlayerById(request.getPlayerId());

        connectResponse.setGameId(game.getId());
        connectResponse.setPlayerId(player.getId());
        connectResponse.setGameTime(game.getGameTime());
        connectResponse.setAdditionalTime(game.getAdditionalTime());
//        connectResponse.setType("connect");

        Set<Board> boardList = game.getBoards();

        List<Board> newListBoard = new ArrayList<>();
        for (Board board : boardList) {
            newListBoard.add(board);
        }

        //gra typu dla 4 graczy
        if (game.getGameType().equals("4")) {

            connectResponse.setGameType("4");

            for (Board b : boardList) {
                if (b.getPlayers().size() == 1) {//1 gracz na szachownicy
                    //sprawdzanie koloru gracza na szachownicy
                    connectResponse.setBoardId(b.getId());
                    b.getPlayers().stream().forEach(v -> {
                        if (v.getColor().equals(Color.WHITE)) {
                            if (v.getTeam().equals(Team.A)) {
                                b.addPlayer(player, Color.BLACK, Team.B);
                                connectResponse.setColor(Color.BLACK);
                                connectResponse.setTeam(Team.B);
                            }

                            if (v.getTeam().equals(Team.B)) {
                                b.addPlayer(player, Color.BLACK, Team.A);
                                connectResponse.setColor(Color.BLACK);
                                connectResponse.setTeam(Team.A);
                            }

                        } else if (v.getColor().equals(Color.BLACK)) {
                            if (v.getTeam().equals(Team.A)) {
                                b.addPlayer(player, Color.WHITE, Team.B);
                                connectResponse.setColor(Color.WHITE);
                                connectResponse.setTeam(Team.B);
                                connectResponse.setPlayerIdMove(player.getId());
                            }

                            if (v.getTeam().equals(Team.B)) {
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
                    if (connectResponse.getBoardIdAdditional() == null) {
                        connectResponse.setBoardIdAdditional(newListBoard.get(1).getId());
                    }

                    //ustawia chat
                    connectResponse.setChatId(chatService.connectToChat(
                                    new ChatConnectRequest(player, connectResponse.getGameId()))
                            .getChatId());

                    return connectResponse;
                }

                if (b.getPlayers().size() == 0) { // 0 graczy na szachownicy
                    System.out.println("proba dolaczenia do bordu: " + b.getId());

                    connectResponse.setBoardId(b.getId());

                    Random random = new Random();
                    if (random.nextInt(2) == 0) {
                        b.addPlayer(playerService.findPlayerById(player.getId()), Color.BLACK, Team.A);
                        connectResponse.setColor(Color.BLACK);
                    } else {
                        b.addPlayer(playerService.findPlayerById(player.getId()), Color.WHITE, Team.B);
                        connectResponse.setColor(Color.WHITE);
                    }

                    boardRepository.save(b);

                    //ustawienie drugiej szachownicy bo
                    //w pierwszej jest wolne miejsce
                    if (connectResponse.getBoardIdAdditional() == null) {
//                    System.out.println("nie");
                        connectResponse.setBoardIdAdditional(newListBoard.get(1).getId());
                    }


                    //ustawia chat
                    connectResponse.setChatId(chatService.connectToChat(
                                    new ChatConnectRequest(player, connectResponse.getGameId()))
                            .getChatId());

                    return connectResponse;
                }

                connectResponse.setBoardIdAdditional(b.getId());//ustawienie dodatkowego boardu id
            }
        }

        //dla typu gry dla 2 graczy
        if (game.getGameType().equals("2")) {
            System.out.println("Dolaczanie");
            connectResponse.setGameType("2");


            // dodanie gracza na 1 szachownice
            if (newListBoard.get(0).getPlayers().size() == 1) {//1 gracz
                //sprawdzanie koloru gracza na szachownicy
                connectResponse.setBoardId(newListBoard.get(0).getId());

                newListBoard.get(0).getPlayers().stream().forEach(v -> {
                    if (v.getColor().equals(Color.WHITE)) {
                        if (v.getTeam().equals(Team.A)) {
                            newListBoard.get(0).addPlayer(player, Color.BLACK, Team.B);
                            connectResponse.setColor(Color.BLACK);
                            connectResponse.setTeam(Team.B);
                        }

                        if (v.getTeam().equals(Team.B)) {
                            newListBoard.get(0).addPlayer(player, Color.BLACK, Team.A);
                            connectResponse.setColor(Color.BLACK);
                            connectResponse.setTeam(Team.A);
                        }

                    } else if (v.getColor().equals(Color.BLACK)) {
                        if (v.getTeam().equals(Team.A)) {
                            newListBoard.get(0).addPlayer(player, Color.WHITE, Team.B);
                            connectResponse.setColor(Color.WHITE);
                            connectResponse.setTeam(Team.B);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                        if (v.getTeam().equals(Team.B)) {
                            newListBoard.get(0).addPlayer(player, Color.WHITE, Team.A);
                            connectResponse.setColor(Color.WHITE);
                            connectResponse.setTeam(Team.A);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                    }
                });

                boardRepository.save(newListBoard.get(0));
            }

            // dodanie gracza na 2 szachownice
            if (newListBoard.get(1).getPlayers().size() == 1) {//1 gracz
                //sprawdzanie koloru gracza na szachownicy
                connectResponse.setBoardIdAdditional(newListBoard.get(1).getId());

                newListBoard.get(1).getPlayers().stream().forEach(v -> {
                    if (v.getColor().equals(Color.WHITE)) {
                        if (v.getTeam().equals(Team.A)) {
                            newListBoard.get(1).addPlayer(player, Color.BLACK, Team.B);
                            connectResponse.setColorSecond(Color.BLACK);
                            connectResponse.setTeam(Team.B);
                        }

                        if (v.getTeam().equals(Team.B)) {
                            newListBoard.get(1).addPlayer(player, Color.BLACK, Team.A);
                            connectResponse.setColorSecond(Color.BLACK);
                            connectResponse.setTeam(Team.A);
                        }

                    } else if (v.getColor().equals(Color.BLACK)) {
                        if (v.getTeam().equals(Team.A)) {
                            newListBoard.get(1).addPlayer(player, Color.WHITE, Team.B);
                            connectResponse.setColorSecond(Color.WHITE);
                            connectResponse.setTeam(Team.B);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                        if (v.getTeam().equals(Team.B)) {
                            newListBoard.get(1).addPlayer(player, Color.WHITE, Team.A);
                            connectResponse.setColorSecond(Color.WHITE);
                            connectResponse.setTeam(Team.A);
                            connectResponse.setPlayerIdMove(player.getId());
                        }

                    }
                });

                boardRepository.save(newListBoard.get(1));

            }

            //ustawia chat
            connectResponse.setChatId(chatService.connectToChat(
                            new ChatConnectRequest(player, connectResponse.getGameId()))
                    .getChatId());

        }

        return connectResponse;

    }

    public NotificateConnectResponse notificateConnectResponse(NotificateConnectRequest request) {

        Player player = playerService.findPlayerByLogin(request.getLogin());

        //ustawianie pozostalych graczy

        NotificateConnectResponse notificateConnectResponse = new NotificateConnectResponse();
        notificateConnectResponse.setType(request.getType());
        notificateConnectResponse.setGameId(request.getGameId());
        notificateConnectResponse.setBoardId(request.getBoardId());
        notificateConnectResponse.setBoardIdAdditional(request.getBoardIdAdditional());
//        notificateConnectResponse.setLogin(playerService.findPlayerById(request.getPlayerId()).getLogin());
        notificateConnectResponse.setLogin(player.getLogin());

        Board boardFirst = boardService.findBoardById(request.getBoardId());
        Board boardSecond = boardService.findBoardById(request.getBoardIdAdditional());

        boardFirst.getPlayers().stream().forEach(v -> {
            if (v.getColor().equals(Color.BLACK)) {
                notificateConnectResponse.setLoginBoardFirstBlack(v.getPlayer().getLogin());
            }
        });

        boardFirst.getPlayers().stream().forEach(v -> {
            if (v.getColor().equals(Color.WHITE)) {
                notificateConnectResponse.setLoginBoardFirstWhite(v.getPlayer().getLogin());
            }
        });

        boardSecond.getPlayers().stream().forEach(v -> {
            if (v.getColor().equals(Color.BLACK)) {
                notificateConnectResponse.setLoginBoardSecondBlack(v.getPlayer().getLogin());
            }
        });

        boardSecond.getPlayers().stream().forEach(v -> {
            if (v.getColor().equals(Color.WHITE)) {
                notificateConnectResponse.setLoginBoardSecondWhite(v.getPlayer().getLogin());
            }
        });

        return notificateConnectResponse;
    }

    public GamePlay makeAMove(GamePlay gamePlay) throws Exception {
        //sprawdzanie czy ktos juz wygral przy aktualnym polozeniu i zapis do bazy TODO
        //sprawdzenie czy gamId istnieje TODO

        //zmiana nastepnego koloru ruchu
        //sprawdzic czy jestes szachowany


        boolean isKing = false;

        if (gamePlay.getFigureNameNew().equals(Pieces.BLACKKING.getPiece())
                || gamePlay.getFigureNameNew().equals(Pieces.WHITEKING.getPiece())) {
            isKing = true;
        }

        //wszystkie ruchy
        MainRules mainRules = new MainRules();
        gamePlay = mainRules.checkRules(gamePlay);


        //awans

        //pat - remis

        //szach TODO

        //sprawdzanie czy ktos juz wygral czyli  mat TODO

        //tylko zbicie króla - bez zbednych dodatków
        if (isKing && gamePlay.getMoveStatus().equals(MoveStatus.OK) && !gamePlay.isCastlingMove()) {
            gamePlay.setGameResult(GameResult.CHECKMATE);
        } else {
            gamePlay.setGameResult(GameResult.EMPTY);
        }

        return gamePlay;
    }
}