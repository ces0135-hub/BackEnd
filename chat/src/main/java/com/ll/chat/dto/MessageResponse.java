package com.ll.chat.dto;


import com.ll.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
// RsData 응답 형식을 위해서 정의 => List 형태로 출력해줌
// GET으로 가져오는 정보 표현
public class MessageResponse {
    private List<ChatMessage> chatMessages;
    private int totalCount; // 메세지 수 세기 => size()로 구현
}
