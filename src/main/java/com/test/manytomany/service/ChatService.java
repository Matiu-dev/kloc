package com.test.manytomany.service;

import com.test.manytomany.model.chat.*;
import com.test.manytomany.model.connect.ChatConnectRequest;
import com.test.manytomany.model.connect.ChatConnectResponse;
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
    public ChatMessageResponse sendMessage(ChatMessageRequest chatMessage) throws Exception {

        ChatMessageResponse chatMessageResponse = new ChatMessageResponse();
        chatMessageResponse.setMessageStatus(MessageStatus.BAD);
        chatMessageResponse.setTeam(chatMessage.getTeam());
        chatMessageResponse.setType(chatMessage.getType());
        chatMessageResponse.setWhisperLogin(null);
        chatMessageResponse.setLogin(chatMessage.getLogin());

        String[] m;

        //bez niczego - wysyla wiadomosc do druzyny
        //nick - do gracza o nicku np /whisper kloc1 wiadomosc
        //all - wysyla wiadomosc do wszystkich /all wiadomosc

        m = chatMessage.getMessage().split(" ");

        if (m[0].equals("/all")) {
            chatMessageResponse.setMessage(chatMessage.getMessage().substring(5));
            chatMessageResponse.setMessageStatus(MessageStatus.OK);
            chatMessageResponse.setMessageCommand(MessageCommand.ALL);
        }

        if (m[0].equals("/whisper") && m.length >= 3) {

            int start = m[0].length() + 1 + m[1].length() + 1;

            chatMessageResponse.setMessage(chatMessage.getMessage().substring(start));
            chatMessageResponse.setMessageStatus(MessageStatus.OK);
            chatMessageResponse.setMessageCommand(MessageCommand.WHISPER);
            chatMessageResponse.setWhisperLogin(m[1]);
        }

        if(chatMessage.getMessage().toCharArray()[0] != '/') {
            chatMessageResponse.setMessage(chatMessage.getMessage());
            chatMessageResponse.setMessageStatus(MessageStatus.OK);
            chatMessageResponse.setMessageCommand(MessageCommand.TEAM);
        }


        return chatMessageResponse;
    }
}
