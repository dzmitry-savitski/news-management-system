package by_.dsavitski.news.controller;

import by_.dsavitski.news.service.CommentService;
import by_.dsavitski.news.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CommentsApiTest {
    @InjectMocks
    CommentsApi commentsApi;

    @Mock
    CommentService commentService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(commentsApi).build();
    }

    @Test
    public void testGetComments() throws Exception {
        final int newsId = 1;
        this.mockMvc.perform(get("/api/comments/" + newsId)).
                andExpect(status().isOk()).
                andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));
        verify(commentService).findByNewsId(newsId);
    }

    @Test
    public void testAddComment() throws Exception {
        final int commentId = 123;
        final String commentBody = "comment body";
        Map<String, String> comment = new LinkedHashMap<>();
        comment.put("id", String.valueOf(commentId));
        comment.put("body", commentBody);

        this.mockMvc.perform(post("/api/comments").
                contentType(TestUtils.APPLICATION_JSON_UTF8).
                content(TestUtils.convertObjectToJsonBytes(comment))
        ).
                andExpect(status().isOk()).
                andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));
        verify(commentService).addComment(commentId, commentBody);
    }
}