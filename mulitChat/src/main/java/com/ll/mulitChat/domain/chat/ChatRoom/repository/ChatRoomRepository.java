package com.ll.mulitChat.domain.chat.ChatRoom.repository;

import com.ll.mulitChat.domain.chat.ChatRoom.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
