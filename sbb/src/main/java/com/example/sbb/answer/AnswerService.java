package com.example.sbb.answer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.sbb.question.Question;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDate.now());

        this.answerRepository.save(answer);
    }
}
