package by_.dsavitski.news.repository;

import by_.dsavitski.news.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends Repository<Comment, Integer> {
    @Query(value = "SELECT * FROM comments where news_id=:newsId", nativeQuery = true)
    public List<Comment> findByNewsId(@Param("newsId") Integer newsId);

    @Modifying
    @Query(value = "INSERT INTO comments(body, news_id) VALUES (:body,:newsId)", nativeQuery = true)
    public int insertByNewsId(@Param("newsId") Integer newsId,
                              @Param("body") String body);
}
