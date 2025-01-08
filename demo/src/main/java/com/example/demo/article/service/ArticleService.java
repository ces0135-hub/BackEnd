package com.example.demo.article.service;

import com.example.demo.article.dto.ArticleDTO;
import com.example.demo.article.entity.Article;
import com.example.demo.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDTO> getList() {
        List<Article> articleList = this.articleRepository.findAll();

        // DTO 구조로 변환 => stream 문법 이용
        List<ArticleDTO> articleDTOList = articleList.stream()
                .map(article -> new ArticleDTO(article))
                .collect(Collectors.toList());
        return articleDTOList;
    }

    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        return optionalArticle.orElse(null);


        //return optionalArticle.map(article -> new ArticleDTO(article)).orElse(null);
        // article -> new ArticleDTO(article)는 Article 객체를 받아서 새로운 ArticleDTO 객체를 생성하는 람다식입니다.
        // article이 Article 타입인 건 자동으로 추론됨
    }

    public Article write(String subject, String content) {
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();
        this.articleRepository.save(article);

        return article;
    }

    public Article update(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);

        this.articleRepository.save(article);

        return article;
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
     }
}
