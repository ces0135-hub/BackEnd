package com.example.sbb.answer;

import com.example.sbb.question.Question;
import com.example.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    // 작성자
    @ManyToOne
    private SiteUser author;

    // 수정일
    private LocalDateTime modifyDate;
}
