package com.test.manytomany.controller;


import com.google.gson.Gson;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.GameResult;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.connect.ConnectRequest;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.service.BoardService;
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

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<ConnectResponse> createAndAddPlayerToBoard(HttpEntity<String> httpEntity) {


        String json = httpEntity.getBody();
        Gson gson = new Gson();
        Player request = gson.fromJson(json, Player.class);

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

        return ResponseEntity.ok(boardService.connectToGame(request));
    }

    @CrossOrigin
    @PostMapping("/gameplay")
    public ResponseEntity<GamePlay> makeMove(@RequestBody GamePlay request) throws Exception {

        log.info("gameplay" + request);
        GamePlay gamePlay = boardService.makeAMove(request);

        if(gamePlay.getGameResult().equals(GameResult.CHECKMATE)){
            gameService.updateGameWinners(gamePlay);
        }

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamePlay.getGameId(), gamePlay);

        return ResponseEntity.ok(gamePlay);
    }
}
