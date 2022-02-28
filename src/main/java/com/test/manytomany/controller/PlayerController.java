package com.test.manytomany.controller;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerRole;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/player")
@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

//    @GetMapping("/createPlayer")
//    public String savePlayer(Model model) {
//        System.out.println("test");
////        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.CREATED);
//        return "createPlayer";
//    }

    @PostMapping("/createPlayer")
    public String greetingSubmit(@ModelAttribute Player player, Model model) {
        player.setPlayerRole(PlayerRole.ROLE_USER);
        playerService.savePlayer(player);

        return "login";
    }
}