package com.test.manytomany.service;

import com.test.manytomany.model.UpdatePlayer;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BoardService boardService;

    public String savePlayer(Player player) {
        //sprawdzanie ilosci znakow w loginie i hasle i czy haslo powtorzone jest takie samo


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        System.out.println(constraintViolations.size() == 0);

        if(constraintViolations.size() == 0 && player.getPassword().equals(player.getRepeatPassword())){
            playerRepository.save(player);
            return "login";
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
    public void deleteByLogin(String login) {
        playerRepository.removeByLogin(login);
    }

    public void updatePlayer(UpdatePlayer updatePlayer) {
        Player player = findPlayerByLogin(updatePlayer.getLogin());

        if(updatePlayer.getNewlogin() != null && !updatePlayer.getNewlogin().equals("")) {
            player.setLogin(updatePlayer.getNewlogin());
        }

        if(updatePlayer.getNewpassword() != null && !updatePlayer.getNewpassword().equals("")) {
            player.setPassword(updatePlayer.getNewpassword());
        }

        savePlayer(player);
    }



}
