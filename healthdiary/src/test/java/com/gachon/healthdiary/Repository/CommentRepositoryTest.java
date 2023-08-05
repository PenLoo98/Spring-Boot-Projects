package com.gachon.healthdiary.Repository;

import com.gachon.healthdiary.Entity.Article;
import com.gachon.healthdiary.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
@Transactional
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @DisplayName("특정 게시글의 모든 댓글 조회")
    @Test
    void findByArticleId() {
        // Case 1: 3번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 3L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            Article article = new Article(3L, "제목 3", "3333");
            Comment a = new Comment(1L, "김씨", "댓글1", article);
            Comment b = new Comment(2L, "박씨", "댓글2", article);
            Comment c = new Comment(3L, "이씨", "댓글2", article);
            List<Comment> expected = Arrays.asList(a,b,c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "3번 글의 모든 댓글을 출력");
        }

        // Case 2: 2번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 2L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            Article article = new Article(2L, "제목 2", "2222");
            Comment a = new Comment(5L, "김씨", "댓글1", article);
            Comment b = new Comment(6L, "이씨", "댓글2", article);
            Comment c = new Comment(7L, "박씨", "댓글2", article);
            List<Comment> expected = Arrays.asList(a,b,c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "2번 글의 모든 댓글을 출력");
        }

        // Case 3: 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "1번 글은 댓글이 없습니다.");
        }
    }

    @DisplayName("특정 닉네임의 모든 댓글 조회")
    @Test
    void findByNickname() {
        // Case 1: "김씨"의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "김씨";

            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            Article A = new Article(3L, "제목 3", "3333");
            Article B = new Article(2L, "제목 2", "2222");

            Comment a = new Comment(1L, "김씨", "댓글1", A);
            Comment b = new Comment(5L, "김씨", "댓글1", B);
            List<Comment> expected = Arrays.asList(a,b);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString());
        }

    }
}