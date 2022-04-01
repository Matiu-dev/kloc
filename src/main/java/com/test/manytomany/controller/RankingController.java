package com.test.manytomany.controller;

import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RankingController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/ranking")
    public String ranking(Model model){

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails){
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.getUserByLogin(((UserDetails) user).getUsername()));
        }else {
            //To-Do
        }

        //pobrac dane o uzytkowniku

        List<Player> arrayListPlayers = playerService.findAllPlayers();
        model.addAttribute("listPlayers", arrayListPlayers);

//        for(Player p: arrayListPlayers) {
//            System.out.println("Player: " +p.getLogin());
//        }



        return "ranking";
    }
}
