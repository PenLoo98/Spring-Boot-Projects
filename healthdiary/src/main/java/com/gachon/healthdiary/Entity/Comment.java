package com.gachon.healthdiary.Entity;

import com.gachon.healthdiary.DTO.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;



@Entity(name="comment")
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
    @JoinColumn(name="article_id")
    private Article article;

    public static Comment dtoToEntity(CommentDTO dto, Article article) {
        // 예외 발생
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }
        if(dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                null,
                dto.getNickname(),
                dto.getBody(),
                article
        );
    }

    public void patch(CommentDTO dto) {
        // 예외 발생
        if(this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 comment id가 입력되었습니다.");
        }
        if(this.getArticle().getId() != dto.getArticleId()){
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 article id가 입력되었습니다.");
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
