package com.gachon.healthdiary.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/random-quote")
    public String randomQuote(Model model) {
        String[] quotes = {
                "산다는것 그것은 치열한 전투이다. -로망로랑",
                "진정으로 웃으려면 고통을 참아야하며 , 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린",
                "행복한 삶을 살기위해 필요한 것은 거의 없다. -마르쿠스 아우렐리우스 안토니우스",
                "신은 용기있는자를 결코 버리지 않는다 -켄러",
                "어리석은 자는 멀리서 행복을 찾고, 현명한 자는 자신의 발치에서 행복을 키워간다 -제임스 오펜하임",
                "너무 소심하고 까다롭게 자신의 행동을 고민하지 말라 . 모든 인생은 실험이다 . 더많이 실험할수록 더나아진다 – 랄프 왈도 에머슨",
                "계단을 밟아야 계단 위에 올라설수 있다, -터키속담",
                "좋은 성과를 얻으려면 한 걸음 한 걸음이 힘차고 충실하지 않으면 안 된다, -단테",
                "일하는 시간과 노는 시간을 뚜렷이 구분하라 . 시간의 중요성을 이해하고 매순간을 즐겁게 보내고 유용하게 활용하라. 그러면 젋은 날은 유쾌함으로 가득찰것이고 늙어서도 후회할 일이 적어질것이며 비록 가난할 때라도 인생을 아름답게 살아갈수있다 – 루이사 메이올콧"
        };
        int random = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[random]);
        return "practiceTemplate/random-quote";
    }

}
