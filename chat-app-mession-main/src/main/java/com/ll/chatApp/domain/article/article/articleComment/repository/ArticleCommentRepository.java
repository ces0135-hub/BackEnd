package com.ll.chatApp.domain.article.article.articleComment.repository;

import com.ll.chatApp.domain.article.article.articleComment.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
    List<ArticleComment> findByAuthorId(Long id);  // id를 기반으로 전체 리스트 조회
}
