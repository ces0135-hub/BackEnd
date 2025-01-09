package com.ll.chatApp.domain.chat.chatMessage.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiV1ChatMessageController {

    @GetMapping("/api/v1/chat/rooms/{roomId}/messages")
    public String getChatMessages(@PathVariable("roomId") Long roomId, @RequestParam(value = "afterChatMessageId", defaultValue = "-1") long afterChatMessageId) {
        return roomId + "번 채팅방, id: " + afterChatMessageId;
    }

    @PostMapping("/api/v1/chat/rooms/{roomId}/messages")
    public String createChatMessage(@PathVariable("roomId") Long roomId) {
        return "채팅방 생성 완료";
    }

}
