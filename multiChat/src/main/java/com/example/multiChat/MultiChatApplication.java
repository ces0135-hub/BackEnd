package com.example.multiChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 생성일, 수정일을 자동으로 넣어줌(EntityListener 관련)
@SpringBootApplication
public class MultiChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiChatApplication.class, args);
	}

}
