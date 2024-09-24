package com.example.bootagain.repository;

import com.example.bootagain.entity.Article;
import com.example.bootagain.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 해당 클래스를 JPA와 연동해 테스팅
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "여러분의 인생 영화는 무엇인가요?", "댓글로 달아주세요");
            Comment a = new Comment(1L, article, "mark", "스파이더맨");
            Comment b = new Comment(2L, article, "jeno", "파묘");
            Comment c = new Comment(3L, article, "fullSun", "올드보이");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력합니다.");
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "가가가", "111");
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "9번 글은 없으므로 댓글은 비어 있어야함");
        }
        /* Case 4: 999번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "99번 글은 없으므로 댓글은 비어 있어야함");
        }
        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = -1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "-1번 글은 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "mark"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "mark";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "여러분의 인생 영화는 무엇인가요?", "댓글로 달아주세요"),
                    nickname, "스파이더맨");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"),
                    nickname, "피자");
            Comment c = new Comment(9L, new Article(6L, "당신의 취미는?", "댓글 고고고"),
                    nickname, "작곡하기");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "mark의 모든 댓글 출력하기");
        }
        /* Case 2: "jeno"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "jeno";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(2L, new Article(4L, "여러분의 인생 영화는 무엇인가요?", "댓글로 달아주세요"), nickname, "파묘");
            Comment b = new Comment(6L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "스키야끼");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname, "넷플릭스 시청");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "jeno의 모든 댓글 출력!!!");
        }
        /* Case 3: null의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = null;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글을 출력");
        }
        /* Case 4: ""의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "\"\"의 모든 댓글을 출력");
        }
    }
}