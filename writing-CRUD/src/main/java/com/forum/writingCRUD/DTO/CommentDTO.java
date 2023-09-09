package com.forum.writingCRUD.DTO;

import com.forum.writingCRUD.Entity.Comment;
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
    private Long forumId;
    private String nickname;
    private String body;

    public static CommentDTO entityToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getForum().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
