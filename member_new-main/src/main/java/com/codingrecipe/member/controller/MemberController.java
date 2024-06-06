package com.codingrecipe.member.controller;
import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        }else{
            //login 실패
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        //어떠한 html로 가져갈 데이터가 있으면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    //@PathVariable는 {id}의 값을 가지고 온다.
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmaile = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmaile);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO, Model model){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @PostMapping("/member/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        int deleteCk = memberService.delete(id);
        //model.addAttribute("member",memberDTO);
        return "index";
    }

}









