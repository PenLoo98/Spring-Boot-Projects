package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.DTO.MemberForm;
import com.gachon.healthdiary.Entity.Member;
import com.gachon.healthdiary.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("")
    public String findAllMember(Model model){
        // Repository 를 이용해 DB의 데이터를 Entity 에 저장
        List<Member> memberList = memberRepository.findAll();

        // 가져온 Entity 를 Model 에 저장
        model.addAttribute("memberList", memberList);

        // Model 을 보여줄 페이지 구현
        return "/members/index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "members/signup";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        // form 데이터를 DTO 로 받아옴.
//        System.out.println(form.toString());
        log.info(form.toString());

        // DTO 를 Entity 로 변환
        Member member = form.toEntity();
        log.info(member.toString());

        // Repository 에게 Entity 를 DB 안에 저장하도록 요청
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/{id}")
    public String findMemberById(@PathVariable Long id, Model model){
        // id에 해당하는 데이터를 Repository가 찾아 Entity에 저장
        Member foundMember = memberRepository.findById(id).orElse(null);

        // Entity에 저장된 데이터를 Model에 저장
        model.addAttribute("foundMember", foundMember);

        // Model을 보여줄 페이지를 구현
        return "/members/show";
    }

    @GetMapping("/{id}/edit")
    public String editMemberById(@PathVariable Long id, Model model){
        // id에 해당하는 데이터를 DB에서 가져옴.
        Member beforeMember = memberRepository.findById(id).orElse(null);

        // 데이터를 Entity -> Model에 저장
        model.addAttribute("beforeMember", beforeMember);

        // Model을 보여줄 페이지 구현
        return "/members/edit";
    }

    @PostMapping("/update")
    public String updateMember(MemberForm form){
        // 1. 데이터를 DTO로 받아 Entity로 변환
        Member editedMember = form.toEntity();
        log.info(editedMember.toString());

        // 2. Entity를 DB에 저장
        // 2-1 기존 회원정보 찾기
        Member target = memberRepository.findById(editedMember.getId()).orElse(null);
        // 2-2 갱신된 데이터 저장
        if(target != null){
            memberRepository.save(editedMember);
        }

        // 뷰페이지 반환
        return "redirect:/members/" + editedMember.getId();
    }
}
