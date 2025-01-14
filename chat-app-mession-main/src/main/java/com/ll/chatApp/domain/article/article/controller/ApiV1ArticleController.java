package com.ll.chatApp.domain.article.article.controller;

import com.ll.chatApp.domain.article.article.dto.ArticleDto;
import com.ll.chatApp.domain.article.article.dto.ArticleModifyRequest;
import com.ll.chatApp.domain.article.article.dto.ArticleWriteRequest;
import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.domain.article.article.service.ArticleService;
import com.ll.chatApp.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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
        Article article = articleService.findById(id).orElse(null);

        return new ArticleDto(article);
    }

    // 게시글 등록
    @PostMapping
    public RsData writeArticle(@Valid @RequestBody ArticleWriteRequest articleWriteRequest) {
        Article article = articleService.write(articleWriteRequest.getTitle(), articleWriteRequest.getContent());

        return RsData.of(
                "200",
                "게시글 작성에 성공했습니다.",
                new ArticleDto(article)
        );
    }

    // 게시글 수정
    @PostMapping("{id}")
    public RsData<ArticleDto> updateArticle(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.findById(id).orElse(null);

        Article modifiedArticle = this.articleService.modify(article, articleModifyRequest.getTitle(), articleModifyRequest.getContent());

        return RsData.of(
                "200",
                "게시글 수정에 성공하였습니다.",
                new ArticleDto(modifiedArticle)
        );
    }

    @DeleteMapping("{id}")
    public RsData<Void> deleteArticle(@PathVariable("id") Long id) {
        this.articleService.delete(id);  // delete 메서드는 void

        return RsData.of(
                "200",
                "게시글 삭제에 성공했습니다.",
                null  // 아무것도 반환하지 않으므로
        );
    }

}
