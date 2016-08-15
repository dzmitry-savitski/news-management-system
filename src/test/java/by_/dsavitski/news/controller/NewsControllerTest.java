package by_.dsavitski.news.controller;

import by_.dsavitski.news.config.AppContext;
import by_.dsavitski.news.config.db.DataSourceContext;
import by_.dsavitski.news.config.db.PersistenceContext;
import by_.dsavitski.news.config.web.WebConfig;
import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Tests should be performed using known test db.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {AppContext.class,
                WebConfig.class,
                DataSourceContext.class,
                PersistenceContext.class})
public class NewsControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    NewsService newsService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNewsList() throws Exception {
        this.mockMvc.perform(get("/news")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("newsPage")).
                andExpect(model().attribute("newsPage", instanceOf(Page.class)));
    }

    @Test
    public void testNewsExists() throws Exception {
        final int newsId = 1;
        News newsOriginal = newsService.findOne(newsId);

        this.mockMvc.perform(get("/news/" + newsId)).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("news")).
                andExpect(model().attribute("news", instanceOf(News.class))).
                andExpect(model().attribute("news", equalTo(newsOriginal)));
    }

    @Test
    public void testNewsNotExists() throws Exception {
        final int unexistingNewsId = 999;
        this.mockMvc.perform(get("/news/" + unexistingNewsId)).
                andExpect(redirectedUrl("/news"));
    }

    @Test
    public void testAddNews() throws Exception {
        this.mockMvc.perform(get("/news/modify")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("news")).
                andExpect(model().attribute("news", instanceOf(News.class)));
    }

    @Test
    public void editExistingNews() throws Exception {
        final int newsId = 1;
        News newsOriginal = newsService.findOne(newsId);

        this.mockMvc.perform(get("/news/modify/" + newsId)).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("news")).
                andExpect(model().attribute("news", instanceOf(News.class))).
                andExpect(model().attribute("news", equalTo(newsOriginal)));
    }

    @Test
    public void editUnExistingNews() throws Exception {
        final int unExistingNewsId = 999;
        this.mockMvc.perform(get("/news/modify/" + unExistingNewsId)).
                andExpect(redirectedUrl("/news"));
    }
}