package com.ll.mulitChat.domain.chat.ChatMessage.repository;

import com.ll.mulitChat.domain.chat.ChatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA의 여러 기능들을 사용 가능하게끔 해줌
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
