package com.test.manytomany.controller;

import com.test.manytomany.model.ChatMessage;
import com.test.manytomany.model.DeletePlayer;
import com.test.manytomany.model.UpdatePlayer;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerRole;
import com.test.manytomany.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/player")
@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/createPlayer")
    public String createPlayer(@ModelAttribute Player player) {

//        System.out.println(player.getRepeatPassword());
        player.setPlayerRole(PlayerRole.ROLE_USER);
        return playerService.savePlayer(player);
    }

    @GetMapping("/{login}")
    public String getPlayer(@PathVariable(name = "login") String login, Model model) {

        Player player = null;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = ((UserDetails) user).getUsername();

        if(user instanceof UserDetails){
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.findPlayerByLogin(((UserDetails) user).getUsername()).getId());

            player = (playerService.findPlayerByLogin(((UserDetails) user).getUsername()));

            if(player != null && login.equals(name)) {

                model.addAttribute("password", player.getPassword());

                return "myprofile";
            }
        }else {
            //To-Do
        }


        return "playerprofile";
    }

    @CrossOrigin
    @PutMapping("/updatePlayer")
    @ResponseBody
    public HttpStatus updatePlayer(@RequestBody UpdatePlayer updatePlayer) {

        System.out.println(updatePlayer.getNewlogin() + " " + updatePlayer.getNewpassword());
        playerService.updatePlayer(updatePlayer);

        return HttpStatus.OK;
    }

    @CrossOrigin
    @DeleteMapping("/deletePlayer")
    @ResponseBody
    public HttpStatus deletePlayer(@RequestBody DeletePlayer deletePlayer) {

        playerService.deleteByLogin(deletePlayer.getLogin());

        return HttpStatus.OK;
    }
}