package com.test.manytomany.service;

import com.test.manytomany.model.*;
import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.PlayerBoard.PlayerBoardId;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.game.WinnerTeam;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public void updateGameWinners(GamePlay gamePlay) {

        Game game = gameRepository.findById(gamePlay.getGameId());

        if(gamePlay.getTeam().equals(Team.A)) {
            game.setWinnerTeam(WinnerTeam.A);
        }

        if(gamePlay.getTeam().equals(Team.B)) {
            game.setWinnerTeam(WinnerTeam.B);
        }

        Set<Board> listBoards = game.getBoards();
//        System.out.println(listBoards.size());

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

        if(outOfTime.getTeam().equals(Team.A)) {
            game.setWinnerTeam(WinnerTeam.A);
        }

        if(outOfTime.getTeam().equals(Team.B)) {
            game.setWinnerTeam(WinnerTeam.B);
        }

        Set<Board> listBoards = game.getBoards();
//        System.out.println(listBoards.size());

        Player player;

        if(game.getGameType().equals("4")) {
            for(Board b: listBoards) {
                for(PlayerBoard pb: b.getPlayers()) {
                    player = pb.getPlayer();
                    if(pb.getTeam().equals(outOfTime.getTeam())){
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

                if(pb.getTeam().equals(outOfTime.getTeam())){
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
        return game;
    }

    public Game updateGameWinners(Disconnect disconnect) {
        Game game = gameRepository.findById(disconnect.getGameId());

        if(disconnect.getTeam().equals(Team.A)) {
            game.setWinnerTeam(WinnerTeam.B);
        }

        if(disconnect.getTeam().equals(Team.B)) {
            game.setWinnerTeam(WinnerTeam.A);
        }

        createGame(game);
        return game;
    }

    public Game findGameById(UUID gameId) {
        return gameRepository.findById(gameId);
    }
}
