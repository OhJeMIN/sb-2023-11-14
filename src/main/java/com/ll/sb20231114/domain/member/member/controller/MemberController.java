package com.ll.sb20231114.domain.member.member.controller;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;
import com.ll.sb20231114.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // final 받은 녀석만 자동으로 생성자 만들어줌 그래서 생성자들 생략 가능
@Validated
public class MemberController {
    //@Autowired // 필드 주입, final은 뺸다.
    private final MemberService memberService;
    private final Rq rq; //rq는 대리자임, 근데 대리자가 넘 똑똑해서 요청 rq를 알고 토스해줌
    /*@Autowired // 만약 생성자가 하나라면 AUtoWired 생략 가능
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @GetMapping("/member/join")
    String showJoin() {
        return "/member/member/join";
    }

    @GetMapping("/member/login")
    String showLogin() {
        return "/member/member/login";
    }


    @Data
    public static class JoinForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm) { // writeform 안에 Notblank 쓰게하기 위해선 Valid 쓴다
        memberService.join(joinForm.password, joinForm.username);
        return rq.redirect("/member/login", "로그인이 완료되었습니다.");
    }

    @Data
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/login")
    String login(@Valid LoginForm loginForm) { // writeform 안에 Notblank 쓰게하기 위해선 Valid 쓴다
        Member member  = memberService.findByUsername(loginForm.username).get();

        if(!member.getPassword().equals(loginForm.password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //TODO 로그인 처리

        return rq.redirect("/article/list", "로그인이 완료되었습니다.");
    }
}





