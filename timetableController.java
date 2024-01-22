package com.hompage.login.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class timetableController {
    @GetMapping("/timeTable")
    public String timeTable() {
        return "timetable";
    }

}
