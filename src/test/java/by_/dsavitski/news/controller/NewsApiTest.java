package by_.dsavitski.news.controller;

import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.service.NewsService;
import by_.dsavitski.news.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class NewsApiTest {
    @InjectMocks
    NewsApi newsApi;

    @Mock
    NewsService newsService;

    @Mock
    MessageSource messageSource;

    private boolean validationErrors = false;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(newsApi).
                setValidator(new testValidator()).
                build();

    }

    @Test
    public void testSaveNewsOk() throws Exception {
        News news = new News("test title", new Date(), "test body");

        this.mockMvc.perform(post("/api/news").
                contentType(TestUtils.APPLICATION_JSON_UTF8).
                content(TestUtils.convertObjectToJsonBytes(news))).
                andExpect(status().isOk()).
                andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));
        verify(newsService).save(news);
    }

    @Test
    public void testSaveNewsError() throws Exception {
        News news = new News("test title", new Date(), "test body");
        validationErrors = true;

        this.mockMvc.perform(post("/api/news").
                contentType(TestUtils.APPLICATION_JSON_UTF8).
                content(TestUtils.convertObjectToJsonBytes(news))).
                andExpect(status().is5xxServerError()).
                andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));
        verify(newsService, never()).save(news);
    }

    @Test
    public void testBadReques() throws Exception {
        this.mockMvc.perform(post("/api/news").
                contentType(TestUtils.APPLICATION_JSON_UTF8).
                content("not json content")).
                andExpect(status().is4xxClientError());
    }

    /**
     * Simple test validator for news
     */
    private class testValidator implements Validator {
        @Override
        public boolean supports(Class<?> clazz) {
            return true;
        }

        @Override
        public void validate(Object target, Errors errors) {
            if (validationErrors) {
                errors.reject("some error");
            }
        }
    }
}