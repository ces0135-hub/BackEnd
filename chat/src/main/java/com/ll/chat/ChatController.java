package com.ll.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

//Controller임을 나타내주는 어노테이션
@Controller
//RequestMapping을 통해 /chat으로 시작하는 모든 요청을 이 컨트롤러가 처리하겠다는 것을 나타냄
@RequestMapping("/chat")
public class ChatController {
    // GetMapping을 통해 /chat/room으로 요청이 들어오면 room() 메소드가 실행되도록 함
    @GetMapping("/room")
    @ResponseBody
    // ResponseBody를 통해 리턴값을 HTTP Response Body에 넣어서 보내줌
    public String room() {
        return "room";
    }
/*
    @PostMapping("/messages")
    @ResponseBody
    public String messages() {
        return "messages";
    }
*/
    @PostMapping("/messages")
    @ResponseBody
    public RsData<ChatMessage> messages() {
        ArrayList<ChatMessage> Chatmessages = new ArrayList<>();

        ChatMessage message = new ChatMessage("test", "content");

        Chatmessages.add(message);
        Chatmessages.add(message);
        Chatmessages.add(message);

        return new RsData(
               "200",
                "메세지 작성 완료",
                Chatmessages
        );
    }

    //GET 요청 보내기
    //@GetMapping("/messages") // localhost:8090/messages
}
