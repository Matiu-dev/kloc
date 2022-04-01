package com.test.manytomany.controller;


import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.BoardService;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BoardService boardService;


    @GetMapping("/")
    public String createGame(@RequestParam String submit,
                             @RequestParam(required=false) String gameJoinId,
                             @RequestParam(required = false) String gameTime,
                             @RequestParam(required = false) String additionalTime,
                             Model model) {//@RequestParam String submit Model model

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails){
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.getUserByLogin(((UserDetails) user).getUsername()));
        }else {
            //To-Do
        }

        System.out.println("gameTime " + gameTime);
        System.out.println("additionalTime " + additionalTime);
        System.out.println("submit " + submit);
        System.out.println("gameId " + gameJoinId);

        if(submit.equals("create")) {
            //przekazac wszystkie parametry na htmla jak wyzej
//            model.addAttribute("submit", submit);
            model.addAttribute("submit", submit);
            model.addAttribute("gameTime", gameTime);
            model.addAttribute("additionalTime", additionalTime);
        }

        if(submit.equals("join")) {
            model.addAttribute("submit", submit);
            model.addAttribute("gameJoinId", gameJoinId);
        }

        return "main";
    }
}