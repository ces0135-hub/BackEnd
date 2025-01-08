package com.example.demo.article.controller;

import com.example.demo.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController  // return에 Thymeleaf가 오지 않기 때문에, 무조건 문자열로 데이터를 보냄
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    // 다건 조회
    @GetMapping("")
    public String list() {
        return "목록";
    }
    // 단건 조회
    @GetMapping("/{id}")
    public String article() {
        return "단건";
    }

    @PostMapping("")
    public String create() {
        return "작성";
    }

    @PatchMapping("/{id}")
    public String modify() {
        return "수정";
    }

    @DeleteMapping("{id}")
    public String delete() {
        return "삭제";
    }

}
