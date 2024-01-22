package com.hompage.login.controller;

import com.hompage.login.domain.Member;
import com.hompage.login.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mypage {
    private MemberService memberService;
    @Autowired
    public Mypage(MemberService memberService) {
        this.memberService = memberService;}

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session){
        String loggedInMemberName = (String) session.getAttribute("loggedInMemberName");
        model.addAttribute("loggedInMemberName", loggedInMemberName);
        String loggedInMemberEmail = (String) session.getAttribute("loggedInMemberEmail");
        model.addAttribute("loggedInMemberEmail", loggedInMemberEmail);

        return "/mypage";}


    @PostMapping("/mypage-process")
    public String updateMyPage(@ModelAttribute MemberForm memberForm,@RequestParam("password2") String password2,HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        // 사용자 정보 업데이트
        if (memberForm.getName() != null && !memberForm.getName().isEmpty()) {
            loggedInMember.setName(memberForm.getName());
        }
        if (memberForm.getEmail() != null && !memberForm.getEmail().isEmpty()) {
            loggedInMember.setEmail(memberForm.getEmail());
        }
        if (memberForm.getUserid() != null && !memberForm.getUserid().isEmpty()) {
            loggedInMember.setUserid(memberForm.getUserid());
        }
        // 확인용 비밀번호가 일치하면 비밀번호 업데이트
        if (memberForm.getPassword().equals(password2)) {
            loggedInMember.setPassword(memberForm.getPassword());
        } else {
            // 확인용 비밀번호가 일치하지 않을 경우 에러 처리
            return "error";
        }

        if (memberForm.getPassword() != null && !memberForm.getPassword().isEmpty()) {
            loggedInMember.setPassword(memberForm.getPassword());
        }


        // 데이터베이스에 업데이트된 정보 저장
        memberService.updateMember(loggedInMember);

        return "redirect:/";
    }



    }


