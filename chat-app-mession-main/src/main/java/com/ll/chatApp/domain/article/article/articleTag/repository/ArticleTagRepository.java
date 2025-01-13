package com.ll.chatApp.domain.article.article.articleTag.repository;

import com.ll.chatApp.domain.article.article.articleTag.entity.ArticleTag;
import com.ll.chatApp.domain.article.article.repository.ArticleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
    // authorId는 Article에 존재하기 때문에, JPA 규칙에 따라 적어줘야함(author가 Article에 존재)
    // 규칙대로 명시하면 JPA가 알아서 찾아줌
    List<ArticleTag> findByArticle_authorId(Long authorId);

    // 이 경우도 마찬가지(author)
    List<ArticleTag> findByArticle_authorUsername(String authorUsername);
}
