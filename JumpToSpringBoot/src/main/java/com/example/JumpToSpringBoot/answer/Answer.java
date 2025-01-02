package com.example.JumpToSpringBoot.answer;

import com.example.JumpToSpringBoot.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    // @ManyToOne은 부모 자식 관계를 갖는 구조에서 사용 => Answer가 Question에 속함
    private Question question;
}
