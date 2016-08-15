package by_.dsavitski.news.repository;

import by_.dsavitski.news.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for work with comments.
 * To simplify work with comments (considering News and Comment relations)
 * there are only custom methods in this repository.
 */
public interface CommentRepository extends Repository<Comment, Integer> {
    /**
     * Returns list of comments by given News id.
     */
    @Query(value = "SELECT * FROM comments where news_id=:newsId", nativeQuery = true)
    List<Comment> findByNewsId(@Param("newsId") Integer newsId);

    /**
     * Creates comment with given news Id and body in DB.
     */
    @Modifying
    @Query(value = "INSERT INTO comments(body, news_id) VALUES (:body,:newsId)", nativeQuery = true)
    int insertByNewsId(@Param("newsId") Integer newsId,
                              @Param("body") String body);
}
