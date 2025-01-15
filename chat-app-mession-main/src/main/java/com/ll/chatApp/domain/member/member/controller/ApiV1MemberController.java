package com.ll.chatApp.domain.member.member.controller;

import com.ll.chatApp.domain.member.member.dto.MemberDto;
import com.ll.chatApp.domain.member.member.dto.MemberRequest;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.domain.member.member.service.MemberService;
import com.ll.chatApp.global.jwt.JwtProvider;
import com.ll.chatApp.global.rsData.RsData;
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
    public RsData<String> login(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.getMember(memberRequest.getUsername());

        // JWT 생성
        String token = jwtProvider.genAccessToken(member);

        return new RsData<>("200", "로그인 성공", token);
    }

    @GetMapping("/logout")
    public void logout() {

    }

    // 내정보 불러오기
    @GetMapping("/me")
    public void me() {

    }
}
