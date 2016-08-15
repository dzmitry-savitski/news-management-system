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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
public class PaginationTest {
    @Autowired
    NewsService newsService;

    @Autowired
    private WebApplicationContext wac;

    @Value("${news.pagination.limit}")
    private int pageSize;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testFirstPage() throws Exception {
        final int pageIndex = 0;
        final PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
        final Page<News> page = newsService.getPage(pageRequest);

        this.mockMvc.perform(get("/news")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("newsPage")).
                andExpect(model().attribute("newsPage", equalTo(page)));
    }

    @Test
    public void testSecondPage() throws Exception {
        final int pageNumber = 1;
        final PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        final Page<News> page = newsService.getPage(pageRequest);

        this.mockMvc.perform(get("/news?page=" + pageNumber)).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("newsPage")).
                andExpect(model().attribute("newsPage", equalTo(page)));
    }

    @Test
    public void testExceededPageNumber() throws Exception {
        final int firstPageNumber = 0;
        final int exceededPageNumber = 999;
        final PageRequest pageRequest = new PageRequest(firstPageNumber, pageSize);
        final Page<News> page = newsService.getPage(pageRequest);

        this.mockMvc.perform(get("/news?page=" + exceededPageNumber)).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("newsPage")).
                andExpect(model().attribute("newsPage", equalTo(page)));
    }
}
