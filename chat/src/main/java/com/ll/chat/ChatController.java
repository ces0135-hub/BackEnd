package com.ll.chat;

import com.ll.chat.dto.MessageResponse;
import com.ll.chat.dto.WriteMessageResponse;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage() {
        ChatMessage ch = new ChatMessage("홍길동", "안녕하세요.");
        chatMessages.add(ch);

        ChatMessage ch2 = new ChatMessage("김철수", "안녕하세요.");
        chatMessages.add(ch2);

        return new RsData("200", "메세지가 작성되었습니다.", new WriteMessageResponse(chatMessages));
    }

    @GetMapping("/messages")
    @ResponseBody
    // public RsData< List<ChatMessage> > getMessages() => 가독성 높이려고 dto로 따로 정의
    public RsData<MessageResponse> getMessages() {



        return new RsData("200", "메세지 가져오기 성공", new MessageResponse(chatMessages));
    }
	/*
	@GetMapping("chat/writeMessage")
	@ResponseBody
	public String getMessage() {
		return "응답입니다.";
	}
	*/
}
