package com.ll.chatApp.domain.article.article.articleTag.entity;

import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ArticleTag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    private String content;

}
