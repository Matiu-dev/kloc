package com.test.manytomany.service;

import com.test.manytomany.chesspiecerules.MainRules;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.GameResult;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.Pieces;
import com.test.manytomany.model.chat.Chat;
import com.test.manytomany.model.connect.ChatConnectRequest;
import com.test.manytomany.model.connect.ChatConnectResponse;
import com.test.manytomany.model.connect.ConnectResponse;
import com.test.manytomany.model.player.Player;
import com.test.manytomany.repository.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat findChatById(Long chatId) {
        return chatRepository.findByid(chatId);
    }

    public Chat findChatByGameId(UUID gameId) {
        return chatRepository.findChatByGameId(gameId);
    }

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    public void saveChat(Chat chat) {
        chatRepository.save(chat);
    }

    public ChatConnectResponse createAndAddPlayerToChat(ChatConnectRequest chatConnectRequest) {

        //tworzy chat i dodaje uzytkownika do niego

        Chat chat = new Chat();
        chat.setGame(gameService.findGameById(chatConnectRequest.getGameId()));
//        chat.getPlayers().add(chatConnectRequest.getPlayer());
        chat.getPlayers().add(chatConnectRequest.getPlayer());


        saveChat(chat);

        ChatConnectResponse chatConnectResponse = new ChatConnectResponse();
        chatConnectResponse.setChatId(chat.getId());

        return chatConnectResponse;
    }

    public ChatConnectResponse connectToChat(ChatConnectRequest request) {
        Chat chat = findChatByGameId(request.getGameId());
        chat.getPlayers().add(request.getPlayer());
        saveChat(chat);

        ChatConnectResponse chatConnectResponse = new ChatConnectResponse();
        chatConnectResponse.setChatId(chat.getId());
        return chatConnectResponse;
    }
//
//    public GamePlay sendMessage(Chat chat) throws Exception {
//
//
//        return chat;
//    }
}
