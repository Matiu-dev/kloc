package com.test.manytomany.controller;

import com.test.manytomany.model.DeletePlayer;
import com.test.manytomany.model.UpdatePlayer;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.model.player.PlayerRole;
import com.test.manytomany.model.player.PlayerStatus;
import com.test.manytomany.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@RequestMapping("/player")
@Slf4j
@Controller
public class PlayerController {

    private boolean invalidateHttpSession = true;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/createPlayer")
    public String createPlayer(@ModelAttribute Player player) {

//        System.out.println(player.getRepeatPassword());
        player.setPlayerRole(PlayerRole.ROLE_USER);
        player.setPlayerStatus(PlayerStatus.ACTIVE);

        return playerService.savePlayer(player);
    }

    @GetMapping("/player/{login}")
    public String getPlayer(@PathVariable(name = "login") String login, Model model) {

        Player player = null;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = ((UserDetails) user).getUsername();

        if (user instanceof UserDetails) {
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.findPlayerByLogin(((UserDetails) user).getUsername()).getId());

            player = (playerService.findPlayerByLogin(((UserDetails) user).getUsername()));

            if (player != null && login.equals(name)) {

                model.addAttribute("password", player.getPassword());

                return "myprofile";
            }
        } else {
            //To-Do
        }


        return "playerprofile";
    }

    @CrossOrigin
    @PutMapping("/updatePassword")
    @ResponseBody
    public HttpStatus updatePassword(@RequestBody UpdatePlayer updatePlayer) {

//        System.out.println(updatePlayer.getNewlogin() + " " + updatePlayer.getNewpassword());
//        boolean check =

        log.info("changing password");

        boolean check = playerService.updatePassword(updatePlayer);

        if (check) {
            SecurityContextHolder.clearContext();
        }

        return HttpStatus.OK;
    }

    @CrossOrigin
    @DeleteMapping("/deletePlayer")
    @ResponseBody
    public HttpStatus deletePlayer(@RequestBody DeletePlayer deletePlayer) {

        playerService.deleteByLogin(deletePlayer.getLogin());

        return HttpStatus.OK;
    }


    public boolean isInvalidateHttpSession() {
        return invalidateHttpSession;
    }

    public void setInvalidateHttpSession(boolean invalidateHttpSession) {
        this.invalidateHttpSession = invalidateHttpSession;
    }
}