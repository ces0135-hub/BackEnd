package com.ll.mulitChat.domain.chat.ChatRoom.entity;

import com.ll.mulitChat.domain.chat.ChatMessage.entity.ChatMessage;
import com.ll.mulitChat.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString(callSuper = true)
public class ChatRoom extends BaseEntity {
    private String name;

    @OneToMany
    private List<ChatMessage> chatMessages;
}
