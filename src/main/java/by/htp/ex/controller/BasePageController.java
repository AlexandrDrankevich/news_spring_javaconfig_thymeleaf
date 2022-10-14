package by.htp.ex.controller;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasePageController {
    @Autowired
    private NewsService newsService;
    private static final String newsOnPageAttribute = "news";

    @RequestMapping("/base_page")
    public String goToBasePage(HttpServletRequest request, Model model) {
        List<News> latestNews;
        int countNews = 5;
        try {
            latestNews = newsService.latestList(countNews);
            model.addAttribute(newsOnPageAttribute, latestNews);
            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }
    }
}
