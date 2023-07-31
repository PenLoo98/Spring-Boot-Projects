package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.DTO.MemberForm;
import com.gachon.healthdiary.Entity.Member;
import com.gachon.healthdiary.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getList() {
        return memberRepository.findAll();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member postMember(MemberForm dto) {
        // 1. dto -> Entity
        Member newMember = dto.toEntity();

        // 2. json데이터에 id값이 입력되었는지 확인
        // id값이 입력되었다면 잘못된 요청이다.
        if(newMember.getId() != null){
            return null;
        }

        // 3. 정상적인 요청 처리
        return memberRepository.save(newMember);
    }
}
