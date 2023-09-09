package com.forum.writingCRUD.Entity;

import com.forum.writingCRUD.DTO.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="forum_comment")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name="forum_id")
    private Forum forum;

    public static Comment dtoToEntity(CommentDTO dto, Forum forum) {
        // 예외 발생
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(dto.getForumId() != forum.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                null,
                dto.getNickname(),
                dto.getBody(),
                forum
        );
    }

    public void patch(CommentDTO dto) {
        // 예외 발생
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 comment id가 입력되었습니다.");
        }
        if(this.getForum().getId() != dto.getForumId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 forum id가 입력되었습니다.");
        }

        // 객체 갱신
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();
        }

    }
}