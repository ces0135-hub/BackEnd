package com.example.JumpToSpringBoot.question;

import java.util.List;
import java.util.Optional;

import com.example.JumpToSpringBoot.DataNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        // Optional은 null을 감싸는 래퍼(wrapper) 객체로, 값이 존재할 수도 있고, 존재하지 않을 수도 있는 상황을 표현함
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}