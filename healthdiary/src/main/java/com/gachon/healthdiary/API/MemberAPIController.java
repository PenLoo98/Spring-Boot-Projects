package com.gachon.healthdiary.API;

import com.gachon.healthdiary.DTO.MemberForm;
import com.gachon.healthdiary.Entity.Member;
import com.gachon.healthdiary.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/members")
public class MemberAPIController {
    @Autowired
    MemberService memberService;

    // GET
    @GetMapping("")
    public List<Member> getMembersList(){
       return memberService.getList();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id){
        return memberService.getMember(id);
    }

    // POST
    @PostMapping("")
    public ResponseEntity<Member> postMember(@RequestBody MemberForm dto){
        Member created = memberService.postMember(dto);
        return created == null ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(created) ;

    }

    // PATCH

    // DELETE

//    @Autowired
//    MemberRepository memberRepository;
//
//    // GET
//    @GetMapping("")
//    public List<Member> getMemberList(){
//        return memberRepository.findAll();
//    }
//    @GetMapping("/{id}")
//    public Optional<Member> getMemberById(@PathVariable Long id){
//        return memberRepository.findById(id);
//    }
//
//    // POST
//    @PostMapping("/create")
//    public Member createMember(@RequestBody MemberForm dto){
//        // 1. 받은 데이터 DTO -> Entity
//        Member newMember = dto.toEntity();
//
//        // 2. Repository에서 Entity 저장
//        return memberRepository.save(newMember);
//    }
//
//    // PATCH
//    @PatchMapping("/{id}")
//    public ResponseEntity<Member> patchMember(@PathVariable Long id, @RequestBody MemberForm dto){
//        // 1. 데이터를 dto -> Entity
//        Member member = dto.toEntity();
//        log.info("id: {}, member: {}",id, member.toString());
//
//        // 2. id에 맞는 데이터가 있는지 확인
//        Member target = memberRepository.findById(id).orElse(null);
//        if(id != member.getId() || target == null){
//            log.info("잘못된 요청! id: {}, target: {}", id, target.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 데이터가 있다면 patch
//        target.patch(member);
//        Member updated = memberRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Member> deleteMember(@PathVariable Long id){
//        // 1. id에 맞는 Entity가 DB에 있는지 확인
//        Member target = memberRepository.findById(id).orElse(null);
//
//        // 2. 없다면 오류 메시지 반환
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 있다면 id에 맞는 Entity 삭제 후 응답 메시지 반환
//        memberRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
}
