package com.ll.chat2412.chat;

import com.ll.chat2412.chat.dto.MessagesResponse;
import com.ll.chat2412.chat.dto.WriteMessageRequest;
import com.ll.chat2412.chat.dto.WriteMessageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();


    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest writeMessageRequest) {

        ChatMessage cm = new ChatMessage(writeMessageRequest.getAuthorName(), writeMessageRequest.getContent());
        chatMessages.add(cm);

        /*
        ChatMessage ch = new ChatMessage("홍길동", "안녕하세요.");
        chatMessages.add(ch);

        ChatMessage ch2 = new ChatMessage("김철수", "안녕하세요.");
        chatMessages.add(ch2);
        */

        return new RsData("200", "메세지가 작성되었습니다.", new WriteMessageResponse(chatMessages));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages() {
        return new RsData("200", "메세지 가져오기 성공", new MessagesResponse(chatMessages));
    }
}