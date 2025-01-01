package com.ll.chat.dto;

import com.ll.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
// RsData 응답 형식을 위해서 정의
public class WriteMessageResponse {

    // 객체 안에 List가 들어가는 형태 => 입력받은 내용의 list를 출력하는 것
    // private List<ChatMessage> chatMessages; => 입력한 메세지 전체가 출력됨.
    // 메세지 단건만 출력되도록 변경
    private ChatMessage chatMessage;
}
