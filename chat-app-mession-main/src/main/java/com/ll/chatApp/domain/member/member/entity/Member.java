package com.ll.chatApp.domain.member.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.chatApp.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)  // 테이블에서 null값 허용 X
    private String username;
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String refreshToken;
}
