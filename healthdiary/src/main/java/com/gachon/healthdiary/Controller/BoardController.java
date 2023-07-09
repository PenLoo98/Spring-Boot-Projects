package com.gachon.healthdiary.Controller;

import com.gachon.healthdiary.Entity.Board;
import com.gachon.healthdiary.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // 컨트롤러의 메서드를 정의

    // 게시물 목록 조회 로직을 구현하고, 조회된 게시물 리스트를 반환합니다.
    @GetMapping("/boards")
    public List<Board> getBoardList() {
        return boardService.getBoardList(); // 게시물 목록을 가져오는 비즈니스 로직을 호출
    }

    // 다른 메서드들도 필요에 따라 추가할 수 있습니다.
}