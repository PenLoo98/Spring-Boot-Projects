package com.gachon.healthdiary.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("user", "User");
        return "greeting";
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("name", "홍길동");
        return "goodbye";
    }
}
