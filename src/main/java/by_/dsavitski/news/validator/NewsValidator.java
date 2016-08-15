package by_.dsavitski.news.validator;

import by_.dsavitski.news.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.util.HtmlUtils;

import java.util.Locale;

/**
 * Represents validator for News object.
 */
@Component
public class NewsValidator implements Validator {
    private static final String ENCODING = "UTF-8";

    @Value("${news.title.length.min}")
    private int titleLengthMin;

    @Value("${news.title.length.max}")
    private int titleLengthMax;

    @Value("${news.body.length.min}")
    private int bodyLengthMin;

    @Value("${news.body.length.max}")
    private int bodyLengthMax;

    @Autowired
    private MessageSource msg;

    /**
     * Suitable only for <code>News.class</code>
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return News.class.equals(aClass);
    }

    /**
     * Main validation method.
     */
    @Override
    public void validate(Object input, Errors errors) {
        News news = (News) input;

        //checking on nulls first
        validateNotNullOrEmpty(news, errors);
        if (errors.hasErrors()) {
            return;
        }

        // escaping html
        escapeHtml(news);

        // final length checks
        validateTitle(news.getTitle(), errors);
        validateBody(news.getBody(), errors);
    }

    /**
     * Checks news object fields on nulls or empty.
     */
    private void validateNotNullOrEmpty(News news, Errors errors) {
        String title = news.getTitle();
        if ((title == null) || (title.isEmpty())) {
            String errorMessage = getMessage("error.news.title.empty");
            errors.reject(errorMessage);
        }

        if (news.getDate() == null) {
            String errorMessage = getMessage("error.news.date.empty");
            errors.reject(errorMessage);
        }

        String body = news.getBody();
        if ((body == null) || (body.isEmpty())) {
            String errorMessage = getMessage("error.news.body.empty");
            errors.reject(errorMessage);
        }
    }

    /**
     * Escapes html to avoid XSS attacks.
     * News date is parsed by JSON mapper, so it filtered in controller.
     */
    private void escapeHtml(News news) {
        news.setTitle(HtmlUtils.htmlEscape(news.getTitle().trim(), ENCODING));
        news.setBody(HtmlUtils.htmlEscape(news.getBody().trim(), ENCODING));
    }

    /**
     * Validates title length.
     */
    private void validateTitle(String title, Errors errors) {
        int titleLength = title.length();
        if ((titleLength < titleLengthMin) || (titleLength > titleLengthMax)) {
            String errorMessage = getMessage("error.news.title.length",
                    titleLengthMin,
                    titleLengthMax);
            errors.reject(errorMessage);
        }
    }

    /**
     * Validates body length.
     */
    private void validateBody(String body, Errors errors) {
        int bodyLength = body.length();
        if ((bodyLength < bodyLengthMin) || (bodyLength > bodyLengthMax)) {
            String errorMessage = getMessage("error.news.body.length",
                    bodyLengthMin,
                    bodyLengthMax);
            errors.reject(errorMessage);
        }
    }

    /**
     * Creates new reject message in errors object.
     */
    private String getMessage(String code, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return msg.getMessage(code, params, locale);
    }
}
