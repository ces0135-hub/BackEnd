package com.example.JumpToSpringBoot.question;

import com.example.JumpToSpringBoot.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id // id 속성을 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 데이터를 저장할 때 해당 속성에 값을 일일이 입력하지 않아도 자동으로 1씩 증가하여 저장
    // GenerationType.IDENTITY는 해당 속성만 별도로 번호가 차례대로 늘어나도록 할 때 사용
    // strategy 옵션을 생략한다면 @GeneratedValue 애너테이션이 지정된 모든 속성에 번호를 차례로 생성하므로 순서가 일정한 고유 번호를 가질 수 없게 된다
    private Integer id;

    @Column(length = 200) // 열의 길이 = 200으로 설정
    private String subject;

    @Column(columnDefinition = "TEXT") // 열에 들어갈 data type
    private String content;
    // @Transient 애너테이션은 엔티티의 속성을 테이블의 열로 만들지 않고 클래스의 속성 기능으로만 사용하고자 할 때 쓴다.
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    // mappedBy는 참조 엔티티의 속성명을 정의한다.
    // 즉, Answer 엔티티에서 Question 엔티티를 참조한 속성인 question을 mappedBy에 전달해야 한다.
    //질문을 삭제하면 그에 달린 답변들도 모두 삭제된다.

    private List<Answer> answerList;
    // 질문에서 답변을 참조하려면 question.getAnswerList()를 호출하면 된다.
}
