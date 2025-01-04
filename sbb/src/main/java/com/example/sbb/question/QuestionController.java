package com.example.sbb.question;

import com.example.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question/")
public class QuestionController {
    private final QuestionService questionService;

    // 질문 전체 불러오기
//    public String list(Model model) {
//        List<Question> questionList = this.questionService.getList();
//        model.addAttribute("questionList", questionList);
//
//        return "question_list";
//    }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        // Page 번호로 조회
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // 질문의 id로 불러오기(클릭해서)
    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question); // question이라는 이름으로 model에 저장

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        //@GetMapping으로 매핑한 questionCreate 메서드에 매개변수로 QuestionForm 객체를 추가했다.
        // 이렇게 하면 이제 GET 방식에서도 question_form 템플릿에 QuestionForm 객체가 전달된다.
        // QuestionForm과 같이 매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용할 수 있다.
        return "question_form";
    }

    /*
    @PostMapping("/create")
    public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
        // TODO 질문을 저장
        this.questionService.create(subject, content);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
    */
    // QuestionForm을 사용하여 답변을 저장하는 createAnswer 메서드로 구현
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

}
