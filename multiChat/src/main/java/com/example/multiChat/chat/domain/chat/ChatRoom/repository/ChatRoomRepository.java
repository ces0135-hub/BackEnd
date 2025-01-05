package com.example.multiChat.chat.domain.chat.ChatRoom.repository;

import com.example.multiChat.chat.domain.chat.ChatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}