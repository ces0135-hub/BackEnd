package com.example.JumpToSpringBoot.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor // 생성자 필요없게 해주는 역할
// final이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할을 한다.
@Controller
@RequestMapping("/question")
public class QuestionController {
    // private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    // 엔티티를 직접 이용하지 않고, service를 이용하도록 구현

    @GetMapping("/list")
    // @ResponseBody
    public String List(Model model) {
        // List<Question> questionList = this.questionRepository.findAll();
        List<Question> questionService = this.questionService.getList();

        // model.addAttribute("questionList", questionList);
        model.addAttribute("questionList", questionService);

        return "question_list"; // templates/question_list.html 불러오기
    }

    @GetMapping(value = "detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        // id로 찾아서 가져오기
        model.addAttribute("question", question);
        return "question_detail";
    }
}
