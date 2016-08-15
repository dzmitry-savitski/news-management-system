package by_.dsavitski.news.validator;

import by_.dsavitski.news.config.AppContext;
import by_.dsavitski.news.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource("/application.properties")
public class NewsValidatorTest {
    @Autowired
    NewsValidator newsValidator;

    @Test
    public void supportsOk() throws Exception {
        assertTrue(newsValidator.supports(News.class));
    }

    @Test
    public void supportsReject() throws Exception {
        assertFalse(newsValidator.supports(Object.class));
    }

    @Test
    public void htmlEscapeWithUtf8() throws Exception {
        final String testString = "йцу'>\">@#a/";
        final String escapedString = "йцу&#39;&gt;&quot;&gt;@#a/";
        News test = new News(testString, new Date(), testString);

        newsValidator.validate(test, mock(Errors.class));

        assertEquals(test.getTitle(), escapedString);
        assertEquals(test.getBody(), escapedString);
    }

    @Test
    public void testNullTitle() throws Exception {
        News test = new News(null, new Date(), "some test news body");
        testNullOrEmptyField(test);
    }

    @Test
    public void testNullDate() throws Exception {
        News test = new News("some valid title", null, "some test news body");
        testNullOrEmptyField(test);
    }

    @Test
    public void testNullBody() throws Exception {
        News test = new News("some valid title", new Date(), null);
        testNullOrEmptyField(test);
    }

    @Test
    public void testEmptyTitle() throws Exception {
        News test = new News("", new Date(), "some test news body");
        testNullOrEmptyField(test);
    }

    @Test
    public void testEmptyBody() throws Exception {
        News test = new News("Some test title", new Date(), "");
        testNullOrEmptyField(test);
    }

    private void testNullOrEmptyField(News test) {
        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);

        newsValidator.validate(test, errors);

        verify(errors, atLeastOnce()).reject(anyString());
    }

    @Test
    public void testShortTitle() throws Exception {
        News test = new News("a", new Date(), "some test news body");
        testField(test);
    }

    @Test
    public void testLongTitle() throws Exception {
        char[] bytes = new char[100_000];
        Arrays.fill(bytes, ' ');
        String longTitle = new String(bytes);
        News test = new News(longTitle, new Date(), "some test news body");
        testField(test);
    }

    @Test
    public void testShortBody() throws Exception {
        News test = new News("some valid title", new Date(), "a");
        testField(test);
    }

    @Test
    public void testLongBody() throws Exception {
        char[] bytes = new char[100_000];
        Arrays.fill(bytes, ' ');
        String longBody = new String(bytes);
        News test = new News("some valid title", new Date(), longBody);
        testField(test);
    }

    private void testField(News test) {
        Errors errors = mock(Errors.class);

        newsValidator.validate(test, errors);

        verify(errors).reject(anyString());
    }

    @Configuration
    @ComponentScan("by_.dsavitski.news.validator.**")
    public static class SpringConfig extends AppContext {
    }
}