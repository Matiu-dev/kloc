package com.test.manytomany.controller;

import com.google.gson.Gson;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.ChatMessage;
import com.test.manytomany.model.chat.Chat;
import com.test.manytomany.model.connect.ChatConnectRequest;
import com.test.manytomany.model.connect.ChatConnectResponse;
import com.test.manytomany.model.connect.ConnectRequest;
import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatService chatService;

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<ChatConnectResponse> createAndAddPlayerToBoard(HttpEntity<String> httpEntity) {

        String json = httpEntity.getBody();
        Gson gson = new Gson();
        ChatConnectRequest request = gson.fromJson(json, ChatConnectRequest.class);

        log.info("create" + request);

//        return ResponseEntity.ok(boardService.saveBoard(request, game));
        return ResponseEntity.ok(chatService.createAndAddPlayerToChat(request));
    }

//    @CrossOrigin
//    @PostMapping("/connect")
//    public ResponseEntity<ConnectResponse> connect(HttpEntity<String> httpEntity) throws InvalidParamException, InvalidGameException {
//
//        String json = httpEntity.getBody();
//        Gson gson = new Gson();
//        ConnectRequest request = gson.fromJson(json, ConnectRequest.class);
//
//        log.info("connect" + request);
//
//        return ResponseEntity.ok(boardService.connectToGame(request));
//    }
//
    @CrossOrigin
    @PostMapping("/message")
    public ResponseEntity<ChatMessage> makeMove(@RequestBody ChatMessage request) throws Exception {

        log.info("gameplay" + request);
//        GamePlay gamePlay = boardService.makeAMove(request);
//
//        if(gamePlay.getGameResult().equals(GameResult.CHECKMATE)){
//            gameService.updateGameWinners(gamePlay);
//        }
//
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getLogin(), request);

        return ResponseEntity.ok(request);
    }
}
