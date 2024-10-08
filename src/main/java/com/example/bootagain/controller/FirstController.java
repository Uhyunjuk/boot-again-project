package com.example.bootagain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetU(Model model) {
        // model 객체가 "togeumyii" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "togeumyii");
        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname","토그미");
        return "goodbye";
    }
}
