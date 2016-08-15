package by_.dsavitski.news.service;

import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.repository.NewsRepository;
import by_.dsavitski.news.service.impl.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NewsServiceTest {
    @InjectMocks
    NewsService newsService = new NewsServiceImpl();

    @Mock
    NewsRepository newsRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test shows that in case of empty db
     * should be returned first empty page.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testEmptyTable() throws Exception {
        // mocking an empty request on the first page
        Page<News> testPage = mock(Page.class);
        when(testPage.isFirst()).thenReturn(true);
        when(testPage.getTotalElements()).thenReturn(0L);
        Pageable pageable = mock(Pageable.class);
        when(newsRepository.findAll(pageable)).thenReturn(testPage);

        final Page<News> pageReceived = newsService.getPage(pageable);

        verify(pageable, never()).first();
        verify(newsRepository).findAll(pageable);
        assertEquals(testPage, pageReceived);
    }

    /**
     * Test shows that if page exceeds total pages
     * first page will be shown.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testExceededPage() throws Exception {
        // mocking an empty request NOT on the first page
        Page<News> testPage = mock(Page.class);
        when(testPage.isFirst()).thenReturn(false);
        when(testPage.getTotalElements()).thenReturn(0L);
        Pageable pageable = mock(Pageable.class);
        when(newsRepository.findAll(pageable)).thenReturn(testPage);

        newsService.getPage(pageable);

        verify(pageable).first();
        verify(newsRepository).findAll(pageable);
    }

    @Test
    public void testFindOne() throws Exception {
        final int testId = 123;

        newsService.findOne(testId);

        verify(newsRepository).findOne(testId);
    }

    @Test
    public void testSave() throws Exception {
        final News testNews = new News();

        newsService.save(testNews);

        verify(newsRepository).save(testNews);
    }
}