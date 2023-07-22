package com.gachon.healthdiary.Repository;

import com.gachon.healthdiary.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
