package com.example.multiChat.chat.domain.chat.ChatMessage.repository;

import com.example.multiChat.chat.domain.chat.ChatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}