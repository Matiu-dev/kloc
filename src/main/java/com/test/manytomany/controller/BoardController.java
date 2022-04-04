package com.test.manytomany.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.*;
import com.test.manytomany.model.connect.*;
import com.test.manytomany.service.BoardService;
import com.test.manytomany.service.ChatService;
import com.test.manytomany.service.GameService;
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

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<ConnectResponse> createAndAddPlayerToBoard(HttpEntity<String> httpEntity) {


        String json = httpEntity.getBody();
        Gson gson = new Gson();
        CreateGameRequest request = gson.fromJson(json, CreateGameRequest.class);

        log.info("create" + request);

//        return ResponseEntity.ok(boardService.saveBoard(request, game));
        return ResponseEntity.ok(boardService.createAndAddPlayerToBoard(request));
    }

    @CrossOrigin
    @PostMapping("/connect")
    public ResponseEntity<ConnectResponse> connect(HttpEntity<String> httpEntity) throws InvalidParamException, InvalidGameException {

        String json = httpEntity.getBody();
        Gson gson = new Gson();
        ConnectRequest request = gson.fromJson(json, ConnectRequest.class);
        log.info("connect" + request);

        ConnectResponse connectResponse = boardService.connectToGame(request);

        return ResponseEntity.ok(connectResponse);
    }

    @CrossOrigin
    @PostMapping("/gameplay")
    public ResponseEntity<Object> makeMove(HttpEntity<String> httpEntity) throws Exception {//requestbody

        String json = httpEntity.getBody();
        Gson gson = new Gson();

        GsonBuilder builder = new GsonBuilder();
        Foo o = builder.create().fromJson(json, Foo.class);

        //jesli obiekt gameplay
        if(o.getType().equals("gameplay")) {
            GamePlay request = gson.fromJson(json, GamePlay.class);

            log.info("gameplay" + request);
            GamePlay gamePlay = boardService.makeAMove(request);

            if(gamePlay.getGameResult().equals(GameResult.CHECKMATE)){
                gameService.updateGameWinners(gamePlay);
            }

            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamePlay.getGameId(), gamePlay);
            return ResponseEntity.ok(gamePlay);
        }

        //jestli obiekt chatMessage
       if(o.getType().equals("message")) {
           ChatMessage request = gson.fromJson(json, ChatMessage.class);

           log.info("ChatMessage " + request);
           simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(), request);
           return ResponseEntity.ok(request);
       }

       //jesli koniec czasu
        if(o.getType().equals("time")) {
            OutOfTime request = gson.fromJson(json, OutOfTime.class);

            System.out.println(request.getTeam());
            System.out.println(request.getGameResult());
            System.out.println(request.getGameId());

            log.info("OutOFTIme: ", request);

            if(request.getGameResult().equals(GameResult.CHECKMATE)){
                gameService.updateGameWinners(request);
            }

            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(), request);

            request.setGameResult(GameResult.CHECKMATE);

            return ResponseEntity.ok(request);
        }

        //powiadomienie o polaczeniu
        if(o.getType().equals("connect")) {
            NotificateConnectRequest request = gson.fromJson(json, NotificateConnectRequest.class);

            log.info("Notificate connect: ", request);

            NotificateConnectResponse notificateResponse = boardService.notificateConnectResponse(request);
            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + notificateResponse.getGameId(), notificateResponse);

            return ResponseEntity.ok(notificateResponse);
        }

        //rozlaczenie jednego z graczy
//        if(o.getType().equals("disconnect")) {
//            Disconnect request = gson.fromJson(json, Disconnect.class);
//
//            log.info("Disconnect: ", request);
//
//            if(!request.getGameResult().equals(GameResult.CHECKMATE)){
//                request = gameService.updateGameWinners(request);
//            }
//
//            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + request.getGameId(), request);
//            return ResponseEntity.ok(request);
//        }

        return ResponseEntity.ok(o);
    }
}
