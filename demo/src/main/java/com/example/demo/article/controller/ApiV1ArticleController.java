package com.example.demo.article.controller;

import com.example.demo.article.dto.ArticleDTO;
import com.example.demo.article.entity.Article;
import com.example.demo.article.request.ArticleCreateRequest;
import com.example.demo.article.request.ArticleModifyRequest;
import com.example.demo.article.response.ArticleResponse;
import com.example.demo.article.response.ArticlesResponse;
import com.example.demo.article.service.ArticleService;
import com.example.demo.global.rsData.RsData;
import jakarta.validation.Valid;
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
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleList = new ArrayList<>();

        // 임시 데이터 밀어넣기
        Article article1 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article1));

        Article article2 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article2));

        Article article3 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article3));

        // Respons Data의 형태 적용시키기
        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));
    }

    // 단건 조회
    @GetMapping("/{id}")
    public RsData<ArticleResponse> article(@PathVariable("id") Long id) {
        // 다건 조회와 마찬가지로 RsData 응답 형식으로 변환
        Article article = new Article("제목1", "내용1");
        ArticleDTO articleDTO = new ArticleDTO(article);
        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    // Postman 이용시 key:value 값 입력해줘야함
    @PostMapping("")
    // public String create(@RequestParam("subject") String subject, @RequestParam("content") String content) {
    // request에 선언한 form을 이용해서 입력받기
    public String create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        // JSON 형태로 입력 받음(Postman에서)
        System.out.println(articleCreateRequest.getSubject());
        System.out.println(articleCreateRequest.getContent());
        return "등록 완료";
    }

    // request에 선언한 form을 이용해서 입력받기
    @PatchMapping("/{id}")
    public String modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        System.out.println(id);
        System.out.println(articleModifyRequest.getSubject());
        System.out.println(articleModifyRequest.getContent());
        return "수정";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(id);
        return "삭제";
    }
}
