package com.gachon.healthdiary.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("user", "User");
        return "practiceTemplate/hi";
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("name", "홍길동");
        return "practiceTemplate/bye";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        return "practiceTemplate/hello";
    }
}
