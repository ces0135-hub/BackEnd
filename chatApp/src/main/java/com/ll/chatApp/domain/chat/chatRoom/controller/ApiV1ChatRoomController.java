package com.ll.chatApp.domain.chat.chatRoom.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatRoomController {

    @GetMapping
    public String getChatRooms() {
        return "채팅방 목록 조회";
    }

    @GetMapping("/{roomId}")
    public String getChatRoom(@PathVariable("roomId") Long roomId) {
        return roomId + "채팅방 단건 조회";
    }

    @PostMapping
    public String createChatRoom() {
        return "채팅방 생성";
    }

}
