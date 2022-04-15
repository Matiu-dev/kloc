package com.test.manytomany.repository;

import com.test.manytomany.model.chat.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChatRepository  extends CrudRepository<Chat, Long> {
    Chat findByid(Long chatId);

    Chat findChatByGameId(UUID gameId);
}
