package com.ll.chat2412.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WriteMessageRequest {
    private String authorName;
    private String content;
}
