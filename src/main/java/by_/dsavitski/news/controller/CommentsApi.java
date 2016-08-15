package by_.dsavitski.news.controller;

import by_.dsavitski.news.entity.Comment;
import by_.dsavitski.news.json.Message;
import by_.dsavitski.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentsApi {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getCommentsForNews(@PathVariable(value = "id") int newsId) {
        List<String> comments = getComments(newsId);
        return new Message(comments);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addComment(@RequestBody Map<String, String> comment) {
        String body = comment.get("body");
        int newsId = Integer.valueOf(comment.get("id"));
        commentService.addComment(newsId, body);
        return new Message("ok");
    }


    private List<String> getComments(int newsId) {
        final List<Comment> commentList = commentService.findByNewsId(newsId);
        List<String> commentsString = new ArrayList<>();
        for (Comment comment : commentList) {
            commentsString.add(comment.getBody());
        }
        return commentsString;
    }
}
