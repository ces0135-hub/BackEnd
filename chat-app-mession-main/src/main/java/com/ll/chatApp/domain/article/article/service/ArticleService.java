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
    public RsData<Article> write(Long authorId, String title, String content) {
        Article article = Article.builder()
                .author(
                        Member.builder()
                                .id(authorId)
                                .build()
                )  // 만들면서 memberId에 해당하는 Member 생성
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of("200", "글 작성 성공", article);
    }

    // ArticleServiceTest에서 .get()을 사용하기 때문에 Optional로 반환
    public Optional<Article> findById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        return optionalArticle;
    }

    @Transactional
    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

        articleRepository.save(article);
    }

    @Transactional  //.save()가 필요없음
    public void modifyComment(ArticleComment comment, String commentBody) {
        comment.setBody(commentBody);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
