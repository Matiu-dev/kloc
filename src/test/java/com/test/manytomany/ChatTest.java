package com.test.manytomany;

import com.test.manytomany.model.chat.ChatMessageRequest;
import com.test.manytomany.model.chat.ChatMessageResponse;
import com.test.manytomany.model.chat.MessageCommand;
import com.test.manytomany.model.chat.MessageStatus;
import com.test.manytomany.service.ChatService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChatTest {



    private static ChatService chatService = new ChatService();

//    @BeforeAll
//    static void initialize() {
//        chatService = new ChatService();
//    }

    @Test
    public void checkMessage() throws Exception {
        //all
        ChatMessageRequest chatMessageAllOKRequest = new ChatMessageRequest();
        chatMessageAllOKRequest.setMessage("/all abc");
        chatMessageAllOKRequest.setLogin("kloc1");

        ChatMessageResponse chatMessageAllOKResponse = chatService.sendMessage(chatMessageAllOKRequest);

        assertEquals(chatMessageAllOKResponse.getMessageStatus(), MessageStatus.OK);
        assertEquals(chatMessageAllOKResponse.getMessage(), "abc");
        assertEquals(chatMessageAllOKResponse.getMessageCommand(), MessageCommand.ALL);

        //wshisper
        ChatMessageRequest chatMessageWhisperOKRequest = new ChatMessageRequest();
        chatMessageWhisperOKRequest.setMessage("/whisper kloc1 abc");
        chatMessageWhisperOKRequest.setLogin("kloc1");

        ChatMessageResponse chatMessageWhisperOKResponse = chatService.sendMessage(chatMessageWhisperOKRequest);

        assertEquals(chatMessageWhisperOKResponse.getMessageStatus(), MessageStatus.OK);
        assertEquals(chatMessageWhisperOKResponse.getMessage(), "abc");
        assertEquals(chatMessageWhisperOKResponse.getMessageCommand(), MessageCommand.WHISPER);

        //team
        ChatMessageRequest chatMessageTeamRequest = new ChatMessageRequest();
        chatMessageTeamRequest.setMessage("abc");
        chatMessageTeamRequest.setLogin("kloc1");

        ChatMessageResponse chatMessageTeamResponse = chatService.sendMessage(chatMessageTeamRequest);

        assertEquals(chatMessageTeamResponse.getMessageStatus(), MessageStatus.OK);
        assertEquals(chatMessageTeamResponse.getMessage(), "abc");
        assertEquals(chatMessageTeamResponse.getMessageCommand(), MessageCommand.TEAM);

    }
}
