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

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findByNewsId(int id) {
        return commentRepository.findByNewsId(id);
    }

    @Override
    public int addComment(int newsId, String comment) {
        // just because it's a test task
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        comment = date + " >>> " + HtmlUtils.htmlEscape(comment, "UTF-8");
        return commentRepository.insertByNewsId(newsId, comment);
    }
}
