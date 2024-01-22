package com.hompage.login.controller;

import com.hompage.login.domain.Member;
import com.hompage.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginRegisterController {
    private MemberService memberService;
    @Autowired
    public LoginRegisterController(MemberService memberService){
        this.memberService=memberService;
    }
    @GetMapping("/register")
    public String resister(){
        return "/register";
    }
    @PostMapping("/register-process")
    public String passwordChecking(MemberForm memberForm,
                                @RequestParam String password2){
        Member member=new Member();
        member.setUserid(memberForm.getUserid());
        member.setEmail(memberForm.getEmail());
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        if (member.getPassword().equals(password2)) {
            memberService.join(member);
            return "redirect:/";
        } else {
            return "error";
        }

    }

}
