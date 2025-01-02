package com.example.multiChat.chat.domain.chat.ChatRoom.controller;

import com.example.multiChat.chat.domain.chat.ChatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
}