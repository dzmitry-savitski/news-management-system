package by_.dsavitski.news.controller;

import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.json.Message;
import by_.dsavitski.news.service.NewsService;
import by_.dsavitski.news.validator.NewsValidator;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * REST controller for work with news.
 */
@RestController
@RequestMapping("/api/news")
public class NewsApi {
    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsValidator newsValidator;

    @Autowired
    private MessageSource messageSource;

    /**
     * Saves or updates given news object.
     * News object validated before saving.
     * In case of errors code 4XX or 5XX returned and message with field
     * <code>body</code>, which contains error message.
     * If saving performs successfully then returned a message
     * with <code>body</code>, which contains success message.
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object saveOrUpdateNews(@RequestBody @Validated News news,
                                   BindingResult bindingResult,
                                   HttpServletResponse response,
                                   Locale locale) {
        if (!bindingResult.hasErrors()) {
            newsService.save(news);
            String message = messageSource.getMessage("pages.news.message.success", null, locale);
            return new Message(message);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            //returning only first error for simplification
            String errorMessage = bindingResult.getAllErrors().get(0).getCode();
            return new Message(errorMessage);
        }
    }

    /**
     * Required for news validator binding.
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(newsValidator);
    }

    /**
     * Handles exception in case of malformed user request.
     * In common cases that would be date field, so date error message returned.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message handleJsonMappingException(JsonMappingException ex, Locale locale) {
        String message = messageSource.getMessage("error.news.date.format", null, locale);
        return new Message(message);
    }
}
