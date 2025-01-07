package com.ll.mulitChat.domain.chat.ChatMessage.service;

import com.ll.mulitChat.domain.chat.ChatMessage.entity.ChatMessage;
import com.ll.mulitChat.domain.chat.ChatMessage.repository.ChatMessageRepository;
import com.ll.mulitChat.domain.chat.ChatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public void write(ChatRoom chatRoom, String writerName, String content) {
        // 채팅 내용 생성
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();

        // 채팅 내용 넣기
        chatMessageRepository.save(chatMessage);
    }
}
