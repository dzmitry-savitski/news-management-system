package by_.dsavitski.news.controller;

import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * News page controller.
 * Mapped on <code>/news</code> URI.
 */
@Controller
@RequestMapping("/news")
@PropertySource("classpath:application.properties")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * Shows news list by given pageable object.
     */
    @RequestMapping
    public String allNews(Model model,
                          Pageable pageable,
                          @Value("${news.pagination.limit}") int pageSize) {
        pageable = updateSize(pageable, pageSize);
        model.addAttribute("newsPage", newsService.getPage(pageable));
        return "news";
    }

    /**
     * Shows page with given single news object.
     * If given id doesn't exist - redirects to <code>/news</code> page.
     */
    @RequestMapping(value = "/{id}")
    public String viewNews(@PathVariable(value = "id") int id,
                           Model model) {
        final News current = newsService.findOne(id);
        if (current != null) {
            model.addAttribute("news", current);
            return "view";
        } else {
            return "redirect:/news";
        }
    }

    /**
     * Shows empty page for adding new news.
     */
    @RequestMapping(value = "/modify")
    public String addNews(Model model) {
        final News event = new News();
        event.setDate(new Date());
        model.addAttribute("news", event);
        return "modify";
    }

    /**
     * Shows edit page with given single news object.
     * If given id doesn't exist - redirects to <code>/news</code> page.
     */
    @RequestMapping(value = "/modify/{id}")
    public String editNews(@PathVariable(value = "id") int id,
                           Model model) {
        final News current = newsService.findOne(id);
        if (current != null) {
            model.addAttribute("news", current);
            return "modify";
        } else {
            return "redirect:/news";
        }
    }

    /**
     * Modifies page size first time.
     * In this case annotation <code>@PageableDefault</code> isn't usable,
     * because page size parameter dynamically received from properties file.
     */
    private Pageable updateSize(final Pageable source, final int neededSize) {
        if (source.getPageSize() != neededSize) {
            return new PageRequest(source.getPageNumber(), neededSize, source.getSort());
        } else {
            return source;
        }
    }
}
