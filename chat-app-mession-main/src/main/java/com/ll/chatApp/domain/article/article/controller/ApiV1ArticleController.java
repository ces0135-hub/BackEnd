package com.ll.chatApp.domain.article.article.controller;

import com.ll.chatApp.domain.article.article.dto.ArticleDto;
import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.domain.article.article.service.ArticleService;
import com.ll.chatApp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;


    // 다건 조회
    @GetMapping
    public List<ArticleDto> getArticles() {
        List<Article> articles = articleService.findAll();

        // ArticleDto 형태로 만들어주기
        List<ArticleDto> articleDtoList = articles.stream()
                .map(ArticleDto::new)
                .toList();

        return articleDtoList;
    }

    // 단건 조회
    // DTO로 반환하는 형태
    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable("id") Long id) {
        // service에서 findById가 Optional<Article>을 return
        // Article article = articleService.findById(id).get();
        // 더 좋은 표현
        Article article = articleService.findById(id).orElseGet(Article::new);

        return new ArticleDto(article);
    }

    // 게시글 등록
    @PostMapping
    public RsData<Article> writeArticle(@RequestBody Article article) {
        return articleService.write(article.getId(), article.getTitle(), article.getContent());
    }

    // 게시글 수정
    @PostMapping("{id}")
    public void updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        this.articleService.modify(article, article.getTitle(), article.getContent());
    }

    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        this.articleService.delete(id);
    }

}
