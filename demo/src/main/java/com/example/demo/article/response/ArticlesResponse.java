package com.example.demo.article.response;

import com.example.demo.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// 다건 조회용
@AllArgsConstructor
@Getter
// RsData<List<ArticleDTO>>를 보기 편하게 하려고 생성
public class ArticlesResponse {
    private final List<ArticleDTO> articleList;  // 조회시 표시될 이름
}