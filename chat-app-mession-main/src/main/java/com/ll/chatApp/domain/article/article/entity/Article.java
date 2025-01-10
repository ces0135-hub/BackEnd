package com.ll.chatApp.domain.article.article.entity;

import com.ll.chatApp.domain.article.article.articleComment.entity.ArticleComment;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    private String title;
    private String content;

    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "article", cascade = ALL)
    @Builder.Default  // 안 붙여주면 comments가 null 값으로 초기화됨
    private List<ArticleComment> comments = new ArrayList<>();

    public void addComment(Member memberAuthor, String commentBody) {
        ArticleComment articleComment = ArticleComment.builder()
                .article(this)
                .author(memberAuthor)
                .body(commentBody)
                .build();

        comments.add(articleComment);   // list는 추가할 때 add 사용
    }

    public void removeComment(ArticleComment articleComment) {
        comments.remove(articleComment);
    }
}
