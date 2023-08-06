package com.gachon.healthdiary.API;

import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles/")
public class CommentAPIController {
    @Autowired
    private CommentService commentService;
        // 1. 댓글 조회
        @GetMapping("/{articleId}/comments")
        public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long articleId){
            // 서비스에 articleId에 맞는 comments 요청
            List<CommentDTO> dtos = commentService.getComments(articleId);
            // 결과 응답
            if(dtos == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }


        // 2. 댓글 생성

        // 3. 댓글 수정

        // 4. 댓글 삭제

}
