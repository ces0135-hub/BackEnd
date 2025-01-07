package com.ll.mulitChat.domain.chat.ChatRoom.service;

import com.ll.mulitChat.domain.chat.ChatRoom.entity.ChatRoom;
import com.ll.mulitChat.domain.chat.ChatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom make(String name) {
        // 채팅방 등록 logic
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        // 객체 넣기
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    public List<ChatRoom> getList() {
        return chatRoomRepository.findAll();
    }

}
