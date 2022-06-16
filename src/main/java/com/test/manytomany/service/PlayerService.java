package com.test.manytomany.service;

import com.test.manytomany.model.PlayerBoard.PlayerBoard;
import com.test.manytomany.model.UpdatePlayer;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.model.game.GameStatus;
import com.test.manytomany.model.game.WinnerTeam;
import com.test.manytomany.model.history.PlayerGameHistory;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerStatus;
import com.test.manytomany.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BoardService boardService;

    public String savePlayer(Player player) {
        //sprawdzanie ilosci znakow w loginie i hasle i czy haslo powtorzone jest takie samo
        //sprawdzanie czy ktos ma juz taki sam nick
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        //System.out.println(constraintViolations.size() == 0);

        boolean checkSameLogin = false;
        List<Player> players = playerRepository.findAll();
        for(Player p: players) {
            if(p.getLogin().equals(player.getLogin())) {
                checkSameLogin = true;
            }
        }

        if(constraintViolations.size() == 0 &&
                player.getPassword().equals(player.getRepeatPassword()) &&
                !checkSameLogin){//!checkSameLogin
            playerRepository.save(player);
            return "login";
        } else {
        }

        return "registration";
    }

    public Player findPlayerByLogin(String login) {
        return playerRepository.findByLogin(login);
    }

    public Player findPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID playerId) {
        playerRepository.deleteById(playerId);
    }

    @Transactional
    public boolean deleteByLogin(String login) {
        Player player = findPlayerByLogin(login);
        player.setPlayerStatus(PlayerStatus.DISABLED);
//        savePlayer(player);
        playerRepository.save(player);

        return true;
    }

    public List<PlayerGameHistory> getPlayerGameHistory(Player player) {
        List<PlayerGameHistory> playerGameHistory = new ArrayList<>();

        Set<PlayerBoard> playerBoard = player.getBoards();

        List<Game> listGames = gameService.findAll();


        listGames.forEach(g -> {

            PlayerGameHistory playerGameHistory1 = new PlayerGameHistory();
            playerGameHistory1.setGameId(g.getId());
            playerGameHistory1.setGameType(g.getGameType());
            playerGameHistory1.setWinnerTeam(g.getWinnerTeam());//zamiast tego wynik gry - wygrana lub przegrana
            playerGameHistory1.setGameTime(g.getGameTime());
            playerGameHistory1.setAdditionalTime(g.getAdditionalTime());
            playerGameHistory1.setGameStatus(g.getGameStatus());

            List<Board> boards = new ArrayList<>(g.getBoards());

            boolean contain = false;
            for(Board b: boards) {
                for(PlayerBoard p: b.getPlayers()){
                    if(p.getPlayer().getLogin().equals(player.getLogin())) {
                        contain = true;
                    }
                }
            }

            if(contain){
                playerGameHistory1.setMoveHistoryFirst(boards.get(0).getMoveHistory());
                playerGameHistory1.setMoveHistorySecond(boards.get(1).getMoveHistory());
            }

            playerGameHistory.add(playerGameHistory1);
        });

//        private UUID gameId;
//        private String gameType;
//        private WinnerTeam winnerTeam;
//        private String gameTime;
//        private String additionalTime;
//        private GameStatus gameStatus;
//        private String moveHistory;

//        playerBoard.stream().forEach(v -> {
//            playerGameHistory.add(new PlayerGameHistory(v.getBoard().getGame().getId(),
//                    v.getBoard().getGame().getGameType(),
//                    v.getBoard().getGame().getWinnerTeam(),
//                    v.getBoard().getGame().getGameTime(),
//                    v.getBoard().getGame().getAdditionalTime(),
//                    v.getBoard().getGame().getGameStatus(),
//                    v.getBoard().getId(),
//                    v.getBoard().getMoveHistory()));
//        });

        return playerGameHistory;
    }

    public boolean updatePassword(UpdatePlayer updatePlayer) {
        Player player = findPlayerByLogin(updatePlayer.getLogin());

        if(updatePlayer.getNewpassword() != null &&
                !updatePlayer.getNewpassword().equals("") &&
        !updatePlayer.getNewpassword().equals(player.getPassword()) &&
        updatePlayer.getNewpassword().equals(updatePlayer.getRepeatnewpassword())) {
            player.setPassword(updatePlayer.getNewpassword());
            player.setRepeatPassword(updatePlayer.getNewpassword());
            playerRepository.save(player);
            return true;
        }

        return false;
    }



}
