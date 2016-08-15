package by_.dsavitski.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index page controller.
 */
@Controller
public class IndexController {
    /**
     * Just redirects to news page.
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/news";
    }
}
