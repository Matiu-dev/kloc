package com.test.manytomany.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class ChatWebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final static String CHAT_ENDPOINT = "/message";

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(CHAT_ENDPOINT).setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
    }

    //    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
//                .setAllowedOrigins("*");
//    }
//
//    @Bean
//    public WebSocketHandler getChatWebSocketHandler() {
//        return new ChatWebSocketHandler();
//    }
}
