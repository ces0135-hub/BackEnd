package com.example.demo.article.controller;

import com.example.demo.article.dto.ArticleDTO;
import com.example.demo.article.entity.Article;
import com.example.demo.article.request.ArticleCreateRequest;
import com.example.demo.article.request.ArticleModifyRequest;
import com.example.demo.article.response.ArticleCreateResponse;
import com.example.demo.article.response.ArticleModifyResponse;
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
        List<ArticleDTO> articleList = this.articleService.getList();
        // RsData 형태 적용시키기
        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));


        // List<ArticleDTO> articleList = new ArrayList<>();
        // 임시 데이터 밀어넣기
        // Article article1 = new Article("제목1", "내용1");
        // articleList.add(new ArticleDTO(article1));
        // Article article2 = new Article("제목1", "내용1");
        // articleList.add(new ArticleDTO(article2));
        // Article article3 = new Article("제목1", "내용1");
        // articleList.add(new ArticleDTO(article3));
    }

    // 단건 조회 => data의 dto를 보여줌
    @GetMapping("/{id}")
    public RsData<ArticleResponse> article(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));


        // 다건 조회와 마찬가지로 RsData 응답 형식으로 변환
//        Article article = new Article("제목1", "내용1");
//        ArticleDTO articleDTO = new ArticleDTO(article);
    }

    // Postman 이용시 key:value 값 입력해줘야함
    @PostMapping("")
    // public String create(@RequestParam("subject") String subject, @RequestParam("content") String content) {
    // request에 선언한 form을 이용해서 입력받기
    public RsData<ArticleCreateResponse> create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {
        Article article = this.articleService.write(articleCreateRequest.getSubject(), articleCreateRequest.getContent());

        return RsData.of("200", "등록 성공", new ArticleCreateResponse(article));


        // JSON 형태로 입력 받음(Postman에서)
        // System.out.println(articleCreateRequest.getSubject());
        // System.out.println(articleCreateRequest.getContent());
    }

    // request에 선언한 form을 이용해서 입력받기
    @PatchMapping("/{id}")
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        // System.out.println(id);
        // System.out.println(articleModifyRequest.getSubject());
        // System.out.println(articleModifyRequest.getContent());
        Article article = this.articleService.getArticle(id);

        // 비어있는 경우
        if (article == null) {
            return RsData.of("500", "%d번 게시물은 존재하지 않습니다".formatted(id), null);
        }

        Article updatedArticle =  this.articleService.update(article, articleModifyRequest.getContent(), articleModifyRequest.getSubject());

        return RsData.of("200", "수정 성공", new ArticleModifyResponse(updatedArticle));
    }

    @DeleteMapping("{id}")
    public RsData<ArticleResponse> delete(@PathVariable("id") Long id) {
        Article article = this.articleService.getArticle(id);  // 삭제할 id

        if (article == null) {
            return RsData.of("500", "%d번 게시물은 존재하지 않습니다".formatted(id), null);
        }

        this.articleService.delete(article);  // delete 호출
        ArticleDTO deletedArticle = new ArticleDTO(article);

        return RsData.of("200", "삭제 성공", new ArticleResponse(deletedArticle));
    }
}
