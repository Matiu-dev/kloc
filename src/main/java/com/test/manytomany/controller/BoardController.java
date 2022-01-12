package com.test.manytomany.controller;


import com.google.gson.Gson;
import com.test.manytomany.exception.InvalidGameException;
import com.test.manytomany.exception.InvalidParamException;
import com.test.manytomany.model.ConnectRequest;
import com.test.manytomany.model.board.Board;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.service.BoardService;
import com.test.manytomany.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.io.UnsupportedEncodingException;

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

    @PostMapping("/create")
    public ResponseEntity<Board> createAndAddPlayerToBoard(HttpEntity<String> httpEntity) {


        String json = httpEntity.getBody();
        Gson gson = new Gson();
        Board request = gson.fromJson(json, Board.class);

        log.info("create" + request);

//        return ResponseEntity.ok(boardService.saveBoard(request, game));
        return ResponseEntity.ok(boardService.saveBoard(request));
    }

    @PostMapping("/connect")
    public ResponseEntity<ConnectRequest> connect(HttpEntity<String> httpEntity) throws InvalidParamException, InvalidGameException {

        String json = httpEntity.getBody();
        Gson gson = new Gson();
        ConnectRequest request = gson.fromJson(json, ConnectRequest.class);

        log.info("connect" + request);

        return ResponseEntity.ok(boardService.connectToBoard(request));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<GamePlay> makeMove(@RequestBody GamePlay request) throws Exception {

        log.info("gameplay" + request);
        GamePlay gamePlay = boardService.makeAMove(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamePlay.getBoardId(), gamePlay);

        return ResponseEntity.ok(gamePlay);
    }
}
