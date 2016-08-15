package by_.dsavitski.news.service;

import by_.dsavitski.news.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    @Transactional(readOnly = true)
    List<Comment> findByNewsId(int id);

    int addComment(int newsId, String comment);
}
