package com.ll.mulitChat.domain.chat.ChatRoom.controller;

import com.ll.mulitChat.domain.chat.ChatRoom.entity.ChatRoom;
import com.ll.mulitChat.domain.chat.ChatRoom.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String showMakeRoom() {
        // Thymeleaf로 html 파일을 불러옴
        return "domain/chat/chatRoom/make";
    }

    // 채팅방 생성
    @PostMapping("/make")
    public String makeRoom(String name) {
        chatRoomService.make(name);
        return "redirect:/chat/room/list";
    }

    @GetMapping("/list")
    public String roomList(Model model) {
        // list.html에 던져 줄  데이터 => Model 객체를 사용해야 함(뷰에 객체를 넣어줄 때 사용)
        List<ChatRoom> chatRooms = chatRoomService.getList();
        model.addAttribute("chatRooms", chatRooms); // key:value 꼴

        // Thymeleaf로 html 파일을 불러옴 => Model을 통해서 List인 chatRooms가 아래에 전달됨
        // 이제 list.html에서 Thymeleaf 문법으로 출력하면 됨
        return "domain/chat/chatRoom/list";
    }
}
