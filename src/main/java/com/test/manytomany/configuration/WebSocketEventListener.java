package com.test.manytomany.configuration;

import com.test.manytomany.model.Disconnect;
import com.test.manytomany.model.GameResult;
import com.test.manytomany.model.PlayerBoard.Team;
import com.test.manytomany.model.game.Game;
import com.test.manytomany.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class WebSocketEventListener {

//eventy
//    SessionConnectedEvent
//    SessionConnectEvent
//    SessionDisconnectEvent
//    SessionSubscribeEvent
//    SessionUnsubscribeEvent

//    private String login = "";
//    private String sessionId = "";

    @Autowired
    private GameService gameService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("Client with username {} connected " + event.getMessage());

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

    }

    //https://stackoverflow.com/questions/55873918/how-to-attach-data-to-socket-connection-in-order-to-get-this-data-on-sessiondis
    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info(sha.getSessionId());
        UUID gameId = UUID.fromString(sha.getSessionId().split(":")[0]);
        String login = sha.getSessionId().split(":")[1];
        Team team = Team.valueOf(sha.getSessionId().split(":")[2]);

        System.out.println(gameId + " " + login + " " + team);

        Disconnect disconnect = new Disconnect();
        disconnect.setGameId(gameId);
        disconnect.setType("disconnect");
        disconnect.setGameResult(GameResult.DISCONNECT);
        disconnect.setTeam(team);

        gameService.updateGameWinners(disconnect);

        disconnect.setGameResult(GameResult.CHECKMATE);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gameId,
                disconnect);

    }
}
