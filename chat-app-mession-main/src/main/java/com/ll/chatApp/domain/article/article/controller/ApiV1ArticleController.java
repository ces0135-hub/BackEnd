package com.ll.chatApp.domain.article.article.controller;

import com.ll.chatApp.domain.article.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;


    // 다건 조회
    @GetMapping
    public void getArticles() {

    }

    // 단건 조회
    @GetMapping("/{id}")
    public void getArticle(@PathVariable("id") Long id) {

    }

    // 게시글 등록
    @PostMapping
    public void writeArticle() {

    }

    // 게시글 수정
    @PostMapping("{id}")
    public void updateArticle(@PathVariable("id") Long id) {

    }

    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable("id") Long id) {

    }

}
