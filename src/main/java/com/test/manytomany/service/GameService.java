package com.test.manytomany.service;

import com.test.manytomany.model.*;
import com.test.manytomany.model.GamePlay.GamePlay;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.game.GameStatus;
import com.test.manytomany.model.game.WinnerTeam;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    public List<Game> findAll() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);

        return games;
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void updateGameWinners(GamePlay gamePlay) {

        Game game = gameRepository.findById(gamePlay.getGameId());
        game.setGameStatus(GameStatus.CLOSED);

        if(gamePlay.getTeam().equals(Team.A)) {
            game.setWinnerTeam(WinnerTeam.A);
        }

        if(gamePlay.getTeam().equals(Team.B)) {
            game.setWinnerTeam(WinnerTeam.B);
        }

        Set<Board> listBoards = game.getBoards();
//        System.out.println(listBoards.size());

        //zapis ruchów do tabeli board
        int number = 0;
        for(Board b: listBoards) {
            if(number == 0){
                b.setMoveHistory(gamePlay.getAlgebraicNotationFirst());
                boardService.saveBoard(b);
            }

            if(number == 1) {
                b.setMoveHistory(gamePlay.getAlgebraicNotationSecond());
                boardService.saveBoard(b);
            }
            number++;
        }

        Player player;

        if(game.getGameType().equals("4")) {
            for(Board b: listBoards) {

                for(PlayerBoard pb: b.getPlayers()) {
                    player = pb.getPlayer();
                    if(pb.getTeam().equals(gamePlay.getTeam())){
                        System.out.println("wygrana gracza o id " + player.getId());
                        player.setWins(pb.getPlayer().getWins()+1);
                        playerService.savePlayer(player);
                    } else{
                        System.out.println("przegrana gracza o id " + player.getId());
                        player.setLoses(pb.getPlayer().getLoses()+1);
                        playerService.savePlayer(player);
                    }

                }
            }
        }

        if(game.getGameType().equals("2")) {
            Board board = listBoards.stream().findFirst().get();
            for(PlayerBoard pb: board.getPlayers()){
                player = pb.getPlayer();

                if(pb.getTeam().equals(gamePlay.getTeam())){
                    System.out.println("wygrana gracza o id " + player.getId());
                    player.setWins(pb.getPlayer().getWins()+1);
                    playerService.savePlayer(player);
                } else{
                    System.out.println("przegrana gracza o id " + player.getId());
                    player.setLoses(pb.getPlayer().getLoses()+1);
                    playerService.savePlayer(player);
                }
            }
        }

        createGame(game);
    }

    public Game updateGameWinners(OutOfTime outOfTime) {
        Game game = gameRepository.findById(outOfTime.getGameId());

        if(game.getGameStatus().equals(GameStatus.OPEN)) {
            game.setGameStatus(GameStatus.CLOSED);

            if (outOfTime.getTeam().equals(Team.A)) {
                game.setWinnerTeam(WinnerTeam.A);
            }

            if (outOfTime.getTeam().equals(Team.B)) {
                game.setWinnerTeam(WinnerTeam.B);
            }

            Set<Board> listBoards = game.getBoards();

            //zapis ruchów do tabeli board
            int number = 0;
            for(Board b: listBoards) {
                if(number == 0){
                    b.setMoveHistory(outOfTime.getAlgebraicNotationFirst());
                    boardService.saveBoard(b);
                }

                if(number == 1) {
                    b.setMoveHistory(outOfTime.getAlgebraicNotationSecond());
                    boardService.saveBoard(b);
                }
                number++;
            }

            Player player;

            if (game.getGameType().equals("4")) {
                for (Board b : listBoards) {
                    for (PlayerBoard pb : b.getPlayers()) {
                        player = pb.getPlayer();
                        if (pb.getTeam().equals(outOfTime.getTeam())) {
                            System.out.println("wygrana gracza o id " + player.getId());
                            player.setWins(pb.getPlayer().getWins() + 1);
                            playerService.savePlayer(player);
                        } else {
                            System.out.println("przegrana gracza o id " + player.getId());
                            player.setLoses(pb.getPlayer().getLoses() + 1);
                            playerService.savePlayer(player);
                        }

                    }
                }
            }

            if (game.getGameType().equals("2")) {
                Board board = listBoards.stream().findFirst().get();
                for (PlayerBoard pb : board.getPlayers()) {
                    player = pb.getPlayer();

                    if (pb.getTeam().equals(outOfTime.getTeam())) {
                        System.out.println("wygrana gracza o id " + player.getId());
                        player.setWins(pb.getPlayer().getWins() + 1);
                        playerService.savePlayer(player);
                    } else {
                        System.out.println("przegrana gracza o id " + player.getId());
                        player.setLoses(pb.getPlayer().getLoses() + 1);
                        playerService.savePlayer(player);
                    }
                }
            }

            createGame(game);
        }
        return game;
    }

    public Game updateGameWinners(Disconnect disconnect) {
        Game game = gameRepository.findById(disconnect.getGameId());


        if(game.getGameStatus().equals(GameStatus.OPEN)){

            game.setGameStatus(GameStatus.CLOSED);

            if(disconnect.getTeam().equals(Team.A)) {
                game.setWinnerTeam(WinnerTeam.B);
            }

            if(disconnect.getTeam().equals(Team.B)) {
                game.setWinnerTeam(WinnerTeam.A);
            }

            createGame(game);
        }

        return game;
    }

    public Game findGameById(UUID gameId) {
        return gameRepository.findById(gameId);
    }
}
