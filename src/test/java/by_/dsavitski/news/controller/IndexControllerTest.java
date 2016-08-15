package by_.dsavitski.news.controller;

import by_.dsavitski.news.repository.CommentRepository;
import by_.dsavitski.news.service.CommentService;
import by_.dsavitski.news.service.NewsService;
import by_.dsavitski.news.service.impl.CommentServiceImpl;
import by_.dsavitski.news.service.impl.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class IndexControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new IndexController()).build();
    }

    @Test
    public void testIndexRedirectsToNews() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/news"));
    }
}

