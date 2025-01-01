package com.ll.chat;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter // Get 함수 만드는 데 필요

// 메세지의 형태 정의
public class ChatMessage {
    private long id;
    private String authorName;
    private String content;
    private LocalDateTime createDate;

    public ChatMessage(String authorName, String content) {
        this.id = ChatMessageIdGenerator.genNextId(); // id를 자동으로 증가시키며 생성 => id를 직접 입력받지 않음
        this.authorName = authorName;
        this.content = content;
        // ChatMessage에 생성 시간을 기록 => 직접 입력받지 않음
        this.createDate = LocalDateTime.now();
    }

    // 메세지의 id를 자동으로 증가시키며 생성하는 클래스
    class ChatMessageIdGenerator {
        private static long id = 0;
        public static long genNextId() {
            return ++id;
        }
    }
}
