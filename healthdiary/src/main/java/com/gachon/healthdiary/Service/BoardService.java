package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.Entity.Board;
import com.gachon.healthdiary.Interface.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    // 다른 비즈니스 로직들도 필요에 따라 추가할 수 있습니다.
}