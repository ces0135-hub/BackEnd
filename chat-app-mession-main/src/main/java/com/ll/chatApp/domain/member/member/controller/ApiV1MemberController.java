package com.ll.chatApp.domain.member.member.controller;

import com.ll.chatApp.domain.member.member.dto.MemberDto;
import com.ll.chatApp.domain.member.member.dto.MemberRequest;
import com.ll.chatApp.domain.member.member.entity.Member;
import com.ll.chatApp.domain.member.member.service.MemberService;
import com.ll.chatApp.global.jwt.JwtProvider;
import com.ll.chatApp.global.rsData.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        // response.addCookie(new Cookie("accessToken", token));
        // HttpOnly로 변경
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        return new RsData<>("200", "로그인 성공");
    }

    @GetMapping("/logout")
    public void logout() {

    }

    // 내정보 불러오기
    @GetMapping("/me")
    public RsData<MemberDto> me(HttpServletRequest request) {  // request 안에 Cookie가 담겨있음
        Cookie[] cookies = request.getCookies();

        String accessToken = "";

        for(Cookie cookie:cookies) {
            if(cookie.getName().equals("accessToken")) {
                accessToken = cookie.getValue();
            }
        }

        Map<String, Object> claims = jwtProvider.getClaims(accessToken);  // getClaims는 Map을 return하니까
        String username = (String) claims.get("username");  // get("key값") 이용

        // getMember(username)은 username으로 회원 정보를 불러오는 메서드
        Member member = this.memberService.getMember(username);

        return new RsData<>("200", "회원정보 조희 성공", new MemberDto(member));
    }
}
