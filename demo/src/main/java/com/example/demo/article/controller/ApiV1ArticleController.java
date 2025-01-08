package com.example.demo.article.controller;

import com.example.demo.article.dto.ArticleDTO;
import com.example.demo.article.entity.Article;
import com.example.demo.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController  // return에 Thymeleaf가 오지 않기 때문에, 무조건 문자열로 데이터를 보냄
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    // 다건 조회
    @GetMapping("")
    public List<ArticleDTO> list() {
        List<ArticleDTO> articleList = new ArrayList<>();

        // 임시 데이터 밀어넣기
        Article article1 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article1));

        Article article2 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article2));

        Article article3 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article3));

        return articleList;
    }
    // 단건 조회
    @GetMapping("/{id}")
    public ArticleDTO article(@PathVariable("id") Long id) {
        Article article = new Article("제목1", "내용1");
        ArticleDTO articleDTO = new ArticleDTO(article);
        return articleDTO;
    }

    // Postman 이용시 key:value 값 입력해줘야함
    @PostMapping("")
    public String create(@RequestParam("subject") String subject, @RequestParam("content") String content) {
        System.out.println(subject);
        System.out.println(content);
        return "등록 완료";
    }

    @PatchMapping("/{id}")
    public String modify(@PathVariable("id") Long id, @RequestParam("subject") String subject, @RequestParam("content") String content) {
        System.out.println(id);
        System.out.println(subject);
        System.out.println(content);
        return "수정";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        return "삭제";
    }
}
