package com.test.manytomany.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.*;
import com.test.manytomany.model.GamePlay.GamePlay;
import com.test.manytomany.model.chat.ChatMessageRequest;
import com.test.manytomany.model.connect.*;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.BoardService;
import com.test.manytomany.service.ChatService;
import com.test.manytomany.service.GameService;
import com.test.manytomany.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private BoardService boardService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private PlayerService playerService;

    @CrossOrigin
    @PostMapping("/create")
    public ConnectResponse createAndAddPlayerToBoard(@RequestBody CreateGameRequest request) {
        log.info("create" + request);

        Player player = playerService.findPlayerByLogin(request.getLogin());

        return boardService.createAndAddPlayerToBoard(request, player);
    }

    @CrossOrigin
    @PostMapping("/connect")
    public ConnectResponse connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect" + request);

        ConnectResponse connectResponse = boardService.connectToGame(request);

        return connectResponse;
    }

    @CrossOrigin
    @PostMapping("/gameplay")
    public Object makeMove(HttpEntity<String> httpEntity) throws Exception {//requestbody

        String json = httpEntity.getBody();
        Gson gson = new Gson();

        GsonBuilder builder = new GsonBuilder();
        Foo o = builder.create().fromJson(json, Foo.class);

        //jesli obiekt gameplay
        if (o.getType().equals("gameplay")) {
            GamePlay request = gson.fromJson(json, GamePlay.class);

            log.info("gameplay" + request);
            GamePlay gamePlay = boardService.makeAMove(request);

            if (gamePlay.getGameResult().equals(GameResult.CHECKMATE)) {
                gameService.updateGameWinners(gamePlay);
            }

            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamePlay.getGameId(), gamePlay);
            return ResponseEntity.ok(gamePlay);
        }

        //jestli obiekt chatMessage
        if (o.getType().equals("message")) {
            ChatMessageRequest request = gson.fromJson(json, ChatMessageRequest.class);

            log.info("ChatMessage " + request);
            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(), chatService.sendMessage(request));
            return chatService.sendMessage(request);
        }

        //jesli koniec czasu
        if (o.getType().equals("time")) {
            OutOfTime request = gson.fromJson(json, OutOfTime.class);

            log.info("OutOFTIme: ", request);

            if (request.getGameResult().equals(GameResult.CHECKMATE)) {
                gameService.updateGameWinners(request);
            }

            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(), request);

            request.setGameResult(GameResult.CHECKMATE);

            return ResponseEntity.ok(request);
        }

        //powiadomienie o polaczeniu
        if (o.getType().equals("connect")) {
            NotificateConnectRequest request = gson.fromJson(json, NotificateConnectRequest.class);

            log.info("Notificate connect: ", request);

            NotificateConnectResponse notificateResponse = boardService.notificateConnectResponse(request);
            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + notificateResponse.getGameId(), notificateResponse);

            return ResponseEntity.ok(notificateResponse);
        }

        return ResponseEntity.ok(o);
    }
}
