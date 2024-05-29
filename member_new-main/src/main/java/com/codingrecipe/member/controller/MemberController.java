package com.codingrecipe.member.controller;
import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //@RequiredArgsConstructor 컨트롤러가 서비스 클래스의 자원 즉 메서드 필드를 사용할 권한이 생긴다.

    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            //login 성공
            //TODO ::  로그인 하기 17:33초 부터 보기 https://www.youtube.com/watch?v=W-XKXKLvV_8&list=PLV9zd3otBRt5ANIjawvd-el3QU594wyx7&index=9
            return "main";
        }else{
            //login 실패
            return "login";
        }
    }

}









