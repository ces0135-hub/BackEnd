package com.ll.mulitChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing // 생성일, 수정일 자동으로 넣어줌
@SpringBootApplication
public class MulitChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulitChatApplication.class, args);
	}

}
