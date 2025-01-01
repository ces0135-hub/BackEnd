package com.ll.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
@AllArgsConstructor
@Getter
public class MessageRequest {
    private Long fromId; // Long은 참조형 => null값도 가능
}
*/
//같은 코드!
public record MessageRequest(Long fromId) {
}
