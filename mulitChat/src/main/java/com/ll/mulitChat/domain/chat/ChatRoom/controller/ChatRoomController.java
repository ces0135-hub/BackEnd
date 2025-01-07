package com.ll.mulitChat.domain.chat.ChatRoom.controller;

import com.ll.mulitChat.domain.chat.ChatRoom.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    @GetMapping("/chat/room/{roomId}")
    @ResponseBody
    public String showRoom(@PathVariable long roomId, @RequestParam(defaultValue = "NoName") String writerName) {
        // RequestParam은 url 뒤에 ?로 오는 부분
        // default 값으로 writerName이 없을 때 출력되는 내용 설정

        return String.format("%d번 채팅방입니다. writer: %s", roomId, writerName);
    }
}
