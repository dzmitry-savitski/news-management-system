package by_.dsavitski.news.controller;

import by_.dsavitski.news.entity.Comment;
import by_.dsavitski.news.json.Message;
import by_.dsavitski.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * REST controller for work with comments.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentsApi {
    @Autowired
    private CommentService commentService;

    /**
     * Receives list of comments by given news id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getCommentsForNews(@PathVariable(value = "id") int newsId) {
        List<String> comments = getComments(newsId);
        return new Message(comments);
    }

    /**
     * Adds given comment to repository.
     *
     * @param comment should be json object with fields <code>id</code>
     *                and <code>body</code>, which correspond to comment fields.
     * @return JSON string message with <code>body="ok"</code>
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addComment(@RequestBody Map<String, String> comment) {
        String body = comment.get("body");
        int newsId = Integer.valueOf(comment.get("id"));
        commentService.addComment(newsId, body);
        return new Message("ok");
    }

    /**
     * Receives comments by given news id and returns list of comment's bodies as strings.
     */
    private List<String> getComments(int newsId) {
        final List<Comment> commentList = commentService.findByNewsId(newsId);
        List<String> commentsString = new ArrayList<>();
        for (Comment comment : commentList) {
            commentsString.add(comment.getBody());
        }
        return commentsString;
    }
}
