package by_.dsavitski.news.service.impl;

import by_.dsavitski.news.entity.Comment;
import by_.dsavitski.news.repository.CommentRepository;
import by_.dsavitski.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Comment service implementation.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Returns list of comments corresponding to given news id.
     */
    @Override
    public List<Comment> findByNewsId(int id) {
        return commentRepository.findByNewsId(id);
    }

    /**
     * Inserts comment into db with given news id and comment text.
     * Just to decorate test task - current date, time and delimiter added to comment.
     */
    @Override
    public int addComment(int newsId, String comment) {
        // just because it's a test task
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        comment = date + " >>> " + HtmlUtils.htmlEscape(comment, "UTF-8");
        return commentRepository.insertByNewsId(newsId, comment);
    }
}
