package com.ll.mulitChat.global.initData;

import com.ll.mulitChat.domain.chat.ChatMessage.service.ChatMessageService;
import com.ll.mulitChat.domain.chat.ChatRoom.entity.ChatRoom;
import com.ll.mulitChat.domain.chat.ChatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod") // 배포 환경이 아닐 때 실행됨
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        return args -> {
            ChatRoom chatRoom1 = chatRoomService.make("공부");
            ChatRoom chatRoom2 = chatRoomService.make("식사");
            ChatRoom chatRoom3 = chatRoomService.make("휴식");

            // 채팅 데이터 넣기
            IntStream.rangeClosed(1, 100).forEach(num -> {
                chatMessageService.write(chatRoom1, "홍길동", "공부 메시지" + num);
            });
            System.out.println("Not Prod");
        };
    }
}