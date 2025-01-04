package com.example.sbb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true는 유일한 값만 저장할 수 있음을 의미한다.
    // 즉, 값을 중복되게 저장할 수 없음을 말한다.
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}