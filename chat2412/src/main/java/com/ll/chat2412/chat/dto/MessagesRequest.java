package com.ll.chat2412.chat.dto;

public record MessagesRequest(Long fromId) {

}

    /*
    public class MessagesRequest {
        private Long formId; // 박싱/언박싱 뭔지 찾아보기 => Long 관련 --> 얘는 null이 가능
    }

    */