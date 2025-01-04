package com.example.sbb.question;

import com.example.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.questionRepository.findAll(pageable);
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

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDate.now());
        this.questionRepository.save(q);
    }
}
