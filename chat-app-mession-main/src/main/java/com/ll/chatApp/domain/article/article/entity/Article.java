package com.ll.chatApp.domain.article.article.entity;

import com.ll.chatApp.domain.article.article.articleComment.entity.ArticleComment;
import com.ll.chatApp.domain.article.article.articleTag.entity.ArticleTag;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    // @OneToMany의 기본 설정
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = ALL, orphanRemoval = true)
    // orphanRemoval = true: 자식 요소를 추적해서 삭제해줌
    @Builder.Default  // 안 붙여주면 comments가 null 값으로 초기화됨
    @ToString.Exclude  // 무한 참조 문제 해결
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


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleTag> tags = new ArrayList<>();

    // 단건 추가
    public void addTag(String tagContent) {
        ArticleTag tag = ArticleTag.builder()
                .article(this)
                .content(tagContent)
                .build();

        tags.add(tag);
    }

    // 다건 추가
    // 가변 파라미터 받기 => 반복문 이용
    public void addTags(String... tagContents) {
        for (String tagContent : tagContents) {
            addTag(tagContent);
        }
    }

    public String getTagsStr() {
        String tagStr = tags
                .stream()
                .map(ArticleTag::getContent)  // 안에 있는 content를 순환하며 호출
                .collect(Collectors.joining(" #"));

        // 빈 문자열 처리
        if(tagStr.isBlank()) {
            return"";
        }

        // 형식: 뒤에 "#"을 붙임 => 자바 #백엔드 #스프링
        return "#" + tagStr;  // 맨 앞의 "#"는 붙여줘야 함
    }
}
