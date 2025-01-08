package com.example.demo.article.response;

import com.example.demo.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 단건 조회용
@AllArgsConstructor
@Getter
public class ArticleResponse {
    private final ArticleDTO article;

}
