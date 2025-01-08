package com.example.demo.article.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 응답 폼 생성하기
@Data  // 이 안에 기본적인 Lombok 기능들이 다 포함됨
public class ArticleCreateRequest {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}