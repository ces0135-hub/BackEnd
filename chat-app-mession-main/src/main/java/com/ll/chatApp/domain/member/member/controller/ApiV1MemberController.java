package com.ll.chatApp.domain.member.member.controller;

import com.ll.chatApp.domain.member.member.dto.MemberDto;
import com.ll.chatApp.domain.member.member.dto.MemberRequest;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.domain.member.member.service.MemberService;
import com.ll.chatApp.global.jwt.JwtProvider;
import com.ll.chatApp.global.rsData.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    // 회원가입
    @PostMapping("/signup")
    public RsData<MemberDto> signup(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.join(memberRequest.getUsername(), memberRequest.getPassword());

        return new RsData<>("200", "회원가입 성공", new MemberDto(member));
    }

    @PostMapping("/login")
    // HttpServletResponse res: Cookie 이용
    public RsData<Void> login(@Valid @RequestBody MemberRequest memberRequest, HttpServletResponse response) {
        Member member = memberService.getMember(memberRequest.getUsername());

        // 토큰 생성
        String token = jwtProvider.genAccessToken(member);

        // 응답 데이터에 accessToken이라는 이름으로 토큰 발급
        response.addCookie(new Cookie("accessToken", token));

        return new RsData<>("200", "로그인 성공");
    }

    @GetMapping("/logout")
    public void logout() {

    }

    // 내정보 불러오기
    @GetMapping("/me")
    public void me() {

    }
}
