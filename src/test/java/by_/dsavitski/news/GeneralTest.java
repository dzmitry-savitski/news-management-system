package by_.dsavitski.news;

import by_.dsavitski.news.config.AppContext;
import by_.dsavitski.news.config.db.DataSourceContext;
import by_.dsavitski.news.config.db.PersistenceContext;
import by_.dsavitski.news.config.web.WebConfig;
import by_.dsavitski.news.entity.Comment;
import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.repository.CommentRepository;
import by_.dsavitski.news.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {AppContext.class,
                WebConfig.class,
                DataSourceContext.class,
                PersistenceContext.class})
public class GeneralTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NewsRepository newsRepository;

    @Test
    @Rollback(value = false)
    public void general() throws Exception {
//        final News one = new News();
//        one.setId(3);
//        List<Comment> list = new ArrayList<>();
//        list.add(new Comment("asd111", one));
//        list.add(new Comment("asd21111", one));
//        one.setComments(list);
//        newsRepository.save(one);
//
//        final List<Comment> byNewsId = commentRepository.findByNewsId(6);
//        final Comment comment = byNewsId.get(0);
//        comment.setBody("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        commentRepository.save(comment);

        Comment comment = new Comment();
        commentRepository.insertByNewsId(1,"aaaaaaaaaaqqq");

        System.out.println(">>>>>>>>>>>>>>>>>>>");
    }

}



//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//public class IndexControllerTest {
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac)
//                .build();
//    }
//
//    @Test
//    public void testIndexRedirectsToNews() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andExpect(status().is3xxRedirection());
//    }
//
//    @Configuration
//    @Import(IndexController.class)
//    public static class SpringConfig extends AppContext {
//    }
//}