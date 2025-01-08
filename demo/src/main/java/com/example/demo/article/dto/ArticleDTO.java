package com.example.demo.article.dto;


import com.example.demo.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

// Entity는 정보를 저장하는데 이용
// DTO는 전달하는 형식 지정 => 민감한 정보는 전달하지 않을 수 있음
@Getter
public class ArticleDTO {
    private final Long id;
    private final String subject;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;


    // final을 붙이면 데이터를 넣어주는 작업(생성자 함수) 필요
    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}
