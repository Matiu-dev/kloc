package com.test.manytomany.controller;


import com.test.manytomany.model.Player;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/")
    public String game(Model model) {

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails){
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.getUserByLogin(((UserDetails) user).getUsername()));
        }else {
            //To-Do
        }

        return "main";
    }
}