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
}
