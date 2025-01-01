package com.ll.chat.dto;


import com.ll.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
// RsData 응답 형식을 위해서 정의
public class MessageResponse {
    private List<ChatMessage> chatMessages;
}
