package com.test.manytomany.configuration;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

//eventy
//    SessionConnectedEvent
//    SessionConnectEvent
//    SessionDisconnectEvent
//    SessionSubscribeEvent
//    SessionUnsubscribeEvent

    private String login = "";

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("Client with username {} connected " + event.getMessage());

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info("login " + sha.getLogin());
        log.info(sha.getMessageHeaders().toString());
        log.info("passcode " + sha.getPasscode());

    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println("Client with username {} disconnected" + event.getSessionId());

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        log.info(sha.getLogin());
        log.info(sha.getMessageHeaders().toString());
    }
}
