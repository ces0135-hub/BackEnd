package com.ll.mulitChat.domain.chat.ChatRoom.controller;

import com.ll.mulitChat.domain.chat.ChatRoom.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
}
