package com.example.sbb.user;

import lombok.Getter;

/*
관리자를 의미하는 ADMIN과 사용자를 의미하는 USER라는 상수를 만들었다.
그리고 ADMIN은 ‘ROLE_ADMIN’, USER는 ‘ROLE_USER’라는 값을 부여했다.
그리고 UserRole의 ADMIN과 USER 상수는 값을 변경할 필요가 없으므로 @Setter 없이 @Getter만 사용할 수 있도록 했다.
*/


@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"), // "ROLE_ADMIN" 값을 ADMIN 상수에 연결
    USER("ROLE_USER");  // "ROLE_ADMIN" 값을 ADMIN 상수에 연결

    UserRole(String value) {  // 생성자: 열거형 상수를 초기화
        this.value = value;
    }

    private String value; // 각 상수에 연결된 값을 저장할 필드
}