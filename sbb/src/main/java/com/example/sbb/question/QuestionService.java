package com.example.sbb.question;

import com.example.sbb.DataNotFoundException;
import com.example.sbb.answer.Answer;
import com.example.sbb.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // Question에 대해서 사용될 메서드들 정의
public class QuestionService {
    private final QuestionRepository questionRepository;

    // 질문 전체 불러오기
//    public List<Question> getList() {
//        return this.questionRepository.findAll();
//    }
    // getList 메서드는 정수 타입의 페이지 번호를 입력받아 해당 페이지의 Page 객체를 리턴하도록 변경했다.
    // Pageable 객체를 생성할 때 사용한 PageRequest.of(page, 10)에서 page는 조회할 페이지의 번호이고,xs 10은 한 페이지에 보여 줄 게시물의 개수를 의미한다.
    // 이렇게 하면 데이터 전체를 조회하지 않고 해당 페이지의 데이터만 조회하도록 쿼리가 변경된다.
//    public Page<Question> getList(int page) {
//        // 등록한 순서대로 표시
//        List<Sort.Order> sorts = new ArrayList<>();
//        sorts.add(Sort.Order.desc("createDate"));
//        Pageable pageable = PageRequest.of(page, 10);
//        return this.questionRepository.findAll(pageable);
//    }
    // 검색어를 포함하여 질문 목록을 조회하기 위해 다시 QuestionService로 돌아와 getList 메서드를 다음과 같이 수정한다.
    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw);
        // 검색어를 의미하는 매개변수 kw를 getList 메서드에 추가하고 kw값으로 Specification 객체를 생성하여 findAll 메서드 호출 시 전달했다.
        //return this.questionRepository.findAll(spec, pageable);

        return this.questionRepository.findAllByKeyword(kw, pageable);
    }

    // id로 질문 불러오기
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("Question is not found.");
        }
    }

    public void create(String subject, String content, SiteUser siteUser) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(siteUser);

        this.questionRepository.save(q);
    }

    // 질문 수정
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());

        this.questionRepository.save(question);
    }

    // 질문 삭제
    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    // 질문 추천(추천인 저장)
    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

    // 검색 기능
    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }
    /*
    q: Root 자료형으로, 즉 기준이 되는 Question 엔티티의 객체를 의미하며 질문 제목과 내용을 검색하기 위해 필요하다.
    u1: Question 엔티티와 SiteUser 엔티티를 아우터 조인(여기서는 JoinType.LEFT로 아우터 조인을 적용한다.)하여 만든 SiteUser 엔티티의 객체이다.
        Question 엔티티와 SiteUser 엔티티는 author 속성으로 연결되어 있어서 q.join("author")와 같이 조인해야 한다.
        u1 객체는 질문 작성자를 검색하기 위해 필요하다.
    a: Question 엔티티와 Answer 엔티티를 아우터 조인하여 만든 Answer 엔티티의 객체이다.
        Question 엔티티와 Answer 엔티티는 answerList 속성으로 연결되어 있어서 q.join("answerList")와 같이 조인해야 한다.
        a 객체는 답변 내용을 검색할 때 필요하다.
    u2: 바로 앞에 작성한 a 객체와 다시 한번 SiteUser 엔티티와 아우터 조인하여 만든 SiteUser 엔티티의 객체로 답변 작성자를 검색할 때 필요하다.

    그리고 검색어(kw)가 포함되어 있는지를 like 키워드로 검색하기 위해 제목, 내용, 질문 작성자, 답변 내용, 답변 작성자 각각에 cb.like를 사용한다.
    최종적으로 cb.or로 OR 검색(여러 조건 중 하나라도 만족하는 경우 해당 항목을 반환하는 검색 조건을 말한다.)이 되게 했다.
    */
}
