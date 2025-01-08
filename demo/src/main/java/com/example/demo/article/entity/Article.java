package com.example.demo.article.entity;

import com.example.demo.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Controller;

@Controller
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Entity
public class Article extends BaseEntity {
    private String subject;
    private String content;
}
