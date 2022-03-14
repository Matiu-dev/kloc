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

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BoardService boardService;


    @GetMapping("/")
    public String game(Model model) {//@RequestParam String submit

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails){
            String name = ((UserDetails) user).getUsername();
            model.addAttribute("username", name);
            model.addAttribute("playerId",
                    playerService.getUserByLogin(((UserDetails) user).getUsername()));
        }else {
            //To-Do
        }

//        if(submit.equals("create")) {
//            ConnectResponse connectResponse = boardService.createAndAddPlayerToBoard
//                    (playerService.findPlayerById
//                            (playerService.getUserByLogin(((UserDetails) user).getUsername())));
//            //przekazac wszystkie parametry na htmla jak wyzej
//            model.addAttribute("color", connectResponse.getColor());
//            model.addAttribute("boardIdFirst", connectResponse.getBoardId());
//        }
//        System.out.println("submit " + submit);

        return "main";
    }


}