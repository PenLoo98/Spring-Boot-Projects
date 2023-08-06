package com.gachon.healthdiary.DTO;

import com.gachon.healthdiary.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommentDTO {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDTO createCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
