package com.ll.chatApp.domain.article.article.articleTag.service;

import com.ll.chatApp.domain.article.article.articleTag.entity.ArticleTag;
import com.ll.chatApp.domain.article.article.articleTag.repository.ArticleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleTagService {
    private final ArticleTagRepository articleTagRepository;

    public List<ArticleTag> findByAuthorId(Long authorId) {
        return articleTagRepository.findByArticle_authorId(authorId);
    }

    public List<ArticleTag> findByAuthorUsername(String authorUsername) {
        return articleTagRepository.findByArticle_authorUsername(authorUsername);
    }


}
