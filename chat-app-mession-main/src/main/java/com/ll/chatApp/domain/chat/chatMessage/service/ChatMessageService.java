package com.ll.chatApp.domain.chat.chatMessage.service;

import com.ll.chatApp.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatApp.domain.chat.chatMessage.repository.ChatMessageRepository;
import com.ll.chatApp.domain.chat.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    // 빌더 형태로 정보 삽입
    public void create(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();

        chatMessageRepository.save(chatMessage);
    }
}
