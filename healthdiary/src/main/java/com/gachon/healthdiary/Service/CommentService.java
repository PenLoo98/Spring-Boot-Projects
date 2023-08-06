package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Repository.ArticleRepository;
import com.gachon.healthdiary.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDTO> getComments(Long articleId) {
        // stream 사용한 방식
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList()); // 자료형 변환: Stream -> List
    }
}
