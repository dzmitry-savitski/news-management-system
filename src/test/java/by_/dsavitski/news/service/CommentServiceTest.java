package by_.dsavitski.news.service;

import by_.dsavitski.news.repository.CommentRepository;
import by_.dsavitski.news.service.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


public class CommentServiceTest {
    @InjectMocks
    CommentService commentService = new CommentServiceImpl();

    @Mock
    CommentRepository commentRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddComment() throws Exception {
        final int newsId = 123;
        final String commentText = "Test comment";

        commentService.addComment(newsId, commentText);

        verify(commentRepository).insertByNewsId(eq(newsId), anyString());
    }

    @Test
    public void findByNewsId() throws Exception {
        final int newsId = 123;

        commentService.findByNewsId(newsId);

        verify(commentRepository).findByNewsId(newsId);
    }

}