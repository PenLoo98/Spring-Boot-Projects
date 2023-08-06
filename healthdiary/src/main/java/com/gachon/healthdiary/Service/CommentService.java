package com.gachon.healthdiary.Service;

import com.gachon.healthdiary.DTO.CommentDTO;
import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Entity.Comment;
import com.gachon.healthdiary.Repository.ArticleRepository;
import com.gachon.healthdiary.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(comment -> CommentDTO.entityToDTO(comment))
                .collect(Collectors.toList()); // 자료형 변환: Stream -> List
    }

    @Transactional
    public CommentDTO createCommentDTO(Long articleId, CommentDTO dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 2. 댓글 entity 생성
        Comment comment = Comment.dtoToEntity(dto, article);

        // 3. 댓글 entity를 DB에 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO로 변환해 반환
        return CommentDTO.entityToDTO(created);
    }
}
