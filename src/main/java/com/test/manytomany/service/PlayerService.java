package com.test.manytomany.service;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BoardService boardService;

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Long getUserByLogin(String login) {
        return playerRepository.findByLogin(login).getId();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findByid(id);
    }

}
