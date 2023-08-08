package com.gachon.healthdiary.API;

import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles/")
public class CommentAPIController {
    @Autowired
    private CommentService commentService;
        // 1. 댓글 조회
        @GetMapping("/{articleId}/comments")
        public ResponseEntity<List<CommentDTO>> commentsGET(@PathVariable Long articleId){
            // 서비스에 articleId에 맞는 comments 요청
            List<CommentDTO> dtos = commentService.getComments(articleId);
            // 결과 응답
            if(dtos == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }

        // 2. 댓글 생성
        @PostMapping("/{articleId}/comments")
        public ResponseEntity<CommentDTO> commentPOST(@PathVariable Long articleId,
                                                      @RequestBody CommentDTO dto){
            // 서비스에서 CREATE 처리
            CommentDTO createdDTO = commentService.postComment(articleId,dto);

            // 결과 응답
            if(createdDTO==null){
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);

        }

        // 3. 댓글 수정
        @PatchMapping("/comments/{commentId}")
        public ResponseEntity<CommentDTO> commentPATCH(@PathVariable Long commentId,
                                                @RequestBody CommentDTO dto){
            // 서비스에서 로직처리
            CommentDTO updatedDTO = commentService.patchComment(commentId, dto);

            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);

        }

        // 4. 댓글 삭제
        @DeleteMapping("/comments/{commentId}")
        public ResponseEntity<CommentDTO> commentDELETE(@PathVariable Long commentId){
            // 서비스에서 로직처리
            CommentDTO deletedDTO = commentService.deleteComment(commentId);

            // 결과 응답
            return ResponseEntity.status(HttpStatus.OK).body(deletedDTO);
        }

}
