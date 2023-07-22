package com.gachon.healthdiary.Repository;

import com.gachon.healthdiary.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적인 메서드 선언이 필요한 경우 여기에 작성합니다.

    // 게시물 키워드 검색
    List<Board> findByTitleContaining(String keyword);
}
