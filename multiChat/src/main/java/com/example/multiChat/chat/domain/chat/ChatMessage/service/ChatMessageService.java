package com.example.multiChat.chat.domain.chat.ChatMessage.service;

import com.example.multiChat.chat.domain.chat.ChatMessage.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
}