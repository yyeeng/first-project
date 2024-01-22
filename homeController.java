package com.hompage.login.controller;

import com.hompage.login.domain.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 세션에서 현재 로그인한 회원의 정보를 읽어옴
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        if (loggedInMember != null && loggedInMember.getName() != null) {
            // 세션이 존재하고 이름이 있는 경우에만 모델에 추가하여 화면에서 사용할 수 있도록 함
            model.addAttribute("loggedInMemberName", loggedInMember.getName());
            model.addAttribute("loggedInMemberEmail", loggedInMember.getEmail());

        }


        return "home";
    }
}
