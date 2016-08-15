package by_.dsavitski.news.service;

import by_.dsavitski.news.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents comments service.
 */
@Transactional
public interface CommentService {
    /**
     * Returns list of comments corresponding to given news id.
     */
    @Transactional(readOnly = true)
    List<Comment> findByNewsId(int id);

    /**
     * Inserts given comment into db.
     */
    int addComment(int newsId, String comment);
}
