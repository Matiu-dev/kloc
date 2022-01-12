package com.test.manytomany.controller;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<?> savePlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.savePlayer(player), HttpStatus.CREATED);
    }
}