package com.example.sbb.question;

import com.example.sbb.answer.Answer;
import com.example.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    // 작성자
    @ManyToOne
    private SiteUser author;

    // 수정일
    private LocalDateTime modifyDate;

    // 추천 관리를 위한 "집합" => 사용자(SiteUser)를 저장
    @ManyToMany
    Set<SiteUser> voter;
}
