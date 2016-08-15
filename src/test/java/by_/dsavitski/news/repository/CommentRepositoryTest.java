package by_.dsavitski.news.repository;

import by_.dsavitski.news.config.AppContext;
import by_.dsavitski.news.config.db.DataSourceContext;
import by_.dsavitski.news.config.db.PersistenceContext;
import by_.dsavitski.news.config.web.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Testing only CommentRepository because there are custom queries in it.
 * Tests should be performed using known test db.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {AppContext.class,
                WebConfig.class,
                DataSourceContext.class,
                PersistenceContext.class})
public class CommentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findByNewsId() throws Exception {
        final int newsId = 1;
        final int springContRows = countRowsInTableWhere("comments", "news_id=" + newsId);

        final int repoCountRows = commentRepository.findByNewsId(newsId).size();

        assertEquals(springContRows, repoCountRows);
    }

    @Test
    public void insertByNewsId() throws Exception {
        final int newsId = 1;
        final int springInitRows = countRowsInTableWhere("comments", "news_id=" + newsId);

        commentRepository.insertByNewsId(newsId, "comment body");

        final int springAfterInsertRows = countRowsInTableWhere("comments", "news_id=" + newsId);

        assertEquals(springAfterInsertRows, (springInitRows + 1));
    }
}