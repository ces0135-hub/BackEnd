package com.example.sbb.question;

import com.example.sbb.answer.AnswerForm;
import com.example.sbb.user.SiteUser;
import com.example.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/question/")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

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
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question); // question이라는 이름으로 model에 저장
        model.addAttribute("answerForm", new AnswerForm()); // AnswerForm 객체를 생성하여 answerForm이라는 이름으로 model에 저장
        return "question_detail";
    }

    // @PreAuthorize("isAuthenticated()") 애너테이션이 붙은 메서드는 로그인한 경우에만 실행된다.
    // 즉, 이 애너테이션을 메서드에 붙이면 해당 메서드는 로그인한 사용자만 호출할 수 있다.
    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
    // QuestionForm을 사용하여 답변을 저장하는 createAnswer 메서드로 구현
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        // 작성자 정보 전달
        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }

    // 질문 수정 기능
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    // 질문 수정 기능
    /*
    POST 형식의 /question/modify/{id} 요청을 처리하기 위해 이와 같이 questionModify 메서드를 추가했다.
    questionModify 메서드는 questionForm의 데이터를 검증하고 로그인한 사용자와 수정하려는 질문의 작성자가 동일한지도 검증한다.
    검증이 통과되면 QuestionService에서 작성한 modify 메서드를 호출하여 질문 데이터를 수정한다.
    그리고 수정이 완료되면 질문 상세 화면(/question/detail/(숫자))으로 리다이렉트한다.
    */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    // 질문 삭제 기능
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/question/list";
    }
}
