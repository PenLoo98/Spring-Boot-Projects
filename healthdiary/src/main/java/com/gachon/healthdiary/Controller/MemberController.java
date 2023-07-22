package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.DTO.MemberForm;
import com.gachon.healthdiary.Entity.Member;
import com.gachon.healthdiary.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("signup")
    public String signup(){
        return "members/signup";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        // form 데이터를 DTO로 받아옴.
        System.out.println(form.toString());

        // DTO를 Entity로 변환
        Member member = form.toEntity();

        // Repository에게 Entity를 DB안에 저장하도록 요청
        Member saved = memberRepository.save(member);

        return "redirect:/";
    }

}
