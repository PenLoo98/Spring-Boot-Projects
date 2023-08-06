package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Entity.Comment;
import com.gachon.healthdiary.Repository.ArticleRepository;
import com.gachon.healthdiary.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDTO> getComments(Long articleId) {
        // 1. 댓글 조회
        List<Comment> commentList = commentRepository.findByArticleId(articleId);

        // 2. Entity -> Dto
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for(int i=0; i < commentList.size(); i++){
            Comment comment = commentList.get(i);
            CommentDTO dto = CommentDTO.createCommentDTO(comment);
            dtos.add(dto);
        }
        // 3. 결과 반환
        return dtos;
    }
}
