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

@Controller
@RequestMapping("/news")
@PropertySource("classpath:application.properties")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping
    public String allNews(Model model,
                          Pageable pageable,
                          @Value("${news.pagination.limit}") int pageSize) {
        pageable = updateSize(pageable, pageSize);
        model.addAttribute("newsPage", newsService.getPage(pageable));
        return "news";
    }

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

    @RequestMapping(value = "/modify")
    public String addNews(Model model) {
        final News event = new News();
        event.setDate(new Date());
        model.addAttribute("news", event);
        return "modify";
    }

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

    private Pageable updateSize(final Pageable source, final int size) {
        return new PageRequest(source.getPageNumber(), size, source.getSort());
    }
}
