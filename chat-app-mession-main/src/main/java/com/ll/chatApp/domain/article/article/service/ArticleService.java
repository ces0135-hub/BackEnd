package com.ll.chatApp.domain.article.article.service;

import com.ll.chatApp.domain.article.article.articleComment.entity.ArticleComment;
import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.domain.article.article.repository.ArticleRepository;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article write(String title, String content) {
        Article article = Article.builder()
                .author(Member.builder().id(1L).build())
                .title(title)
                .content(content)
                .build();

        return articleRepository.save(article);
    }

    // ArticleServiceTest에서 .get()을 사용하기 때문에 Optional로 반환
    public Optional<Article> findById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        return optionalArticle;
    }

    @Transactional
    public Article modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        return article;
    }

    @Transactional  //.save()가 필요없음
    public void modifyComment(ArticleComment comment, String commentBody) {
        comment.setBody(commentBody);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void delete(Long id) {
        this.articleRepository.deleteById(id);
    }
}
