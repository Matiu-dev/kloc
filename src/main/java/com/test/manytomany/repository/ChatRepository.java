package com.test.manytomany.repository;

import com.test.manytomany.model.chat.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository  extends CrudRepository<Chat, Long> {
    Chat findByid(Long chatId);

    Chat findChatByGameId(Long gameId);
}
