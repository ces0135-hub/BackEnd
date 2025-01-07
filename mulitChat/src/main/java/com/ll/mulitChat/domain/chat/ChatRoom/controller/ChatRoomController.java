package com.ll.mulitChat.domain.chat.ChatRoom.controller;

import com.ll.mulitChat.domain.chat.ChatRoom.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/chat/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    @GetMapping("/{roomId}")
    // @ResponseBody // JSON 형태로 문자열을 보낼 때 사용
    public String showRoom(@PathVariable long roomId, @RequestParam(defaultValue = "NoName") String writerName) {
        // RequestParam은 url 뒤에 ?로 오는 부분
        // default 값으로 writerName이 없을 때 출력되는 내용 설정

        // return String.format("%d번 채팅방입니다. writer: %s", roomId, writerName);
        // Thymeleaf로 html 파일을 불러옴
        return "domain/chat/chatRoom/room";
    }

    @GetMapping("/make")
    public String makeRoom() {
        // Thymeleaf로 html 파일을 불러옴
        return "domain/chat/chatRoom/make";
    }

    @GetMapping("/list")
    public String roomList() {
        // Thymeleaf로 html 파일을 불러옴
        return "domain/chat/chatRoom/list";
    }
}
