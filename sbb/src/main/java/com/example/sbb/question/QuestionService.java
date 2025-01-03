package com.example.sbb.question;

import com.example.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // Question에 대해서 사용될 메서드들 정의
public class QuestionService {
    private final QuestionRepository questionRepository;

    // 질문 전체 불러오기
    public List<Question> getList() {
        return this.questionRepository.findAll();
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
        q.setCreateDate(LocalDate.from(LocalDateTime.now()));
        this.questionRepository.save(q);
    }
}
