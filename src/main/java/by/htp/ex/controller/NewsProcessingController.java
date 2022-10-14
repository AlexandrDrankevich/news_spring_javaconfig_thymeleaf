package by.htp.ex.controller;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/news")
public class NewsProcessingController {

    @Autowired
    private NewsService newsService;
    private static final String newsAttribute = "news";
    private static final String addNewsAttribute = "addnews";
    private static final String addNewsStatus = "active";
    private static final String editNewsStatus = "active";
    private static final String editViewAttribute = "editView";
    private static final String newsIdParam = "id";
    private static final String deleteMessageAttribute = "deleteMessage";
    private static final String deleteMessage = "delete ok";
    private static final String newsMessageAttribute = "newsMessage";
    private static final String newsMessage = "News saved!";

    @RequestMapping("/addNewsForm")
    public String showAddNewsForm(@ModelAttribute(newsAttribute) News news, HttpServletRequest request) {
        request.setAttribute(addNewsAttribute, addNewsStatus);
        return "baseLayout";
    }

    @RequestMapping("/saveNews")
    public String addNews(HttpServletRequest request, @ModelAttribute(newsAttribute) News news,
                          RedirectAttributes attr) {
        try {
            newsService.save(news);
            attr.addAttribute(newsMessageAttribute, newsMessage);
            return "redirect:/viewNews/" + news.getIdNews();
        } catch (ServiceException e) {
            return "error";
        }
    }

    @RequestMapping("/editNews/{id}")
    public String showEditNewsForm(@PathVariable("id") String id,  Model model) {
        try {
            News news = newsService.findById(Integer.parseInt(id));
            model.addAttribute(newsAttribute, news);
            model.addAttribute(addNewsAttribute, editNewsStatus);
            model.addAttribute(editViewAttribute, editNewsStatus);
            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }
    }

    @RequestMapping("/delete")
    public String deleteNews(HttpServletRequest request, RedirectAttributes attr) {
        String[] idNews = request.getParameterValues(newsIdParam);
        try {
            if (idNews == null) {
                return "redirect:/newsList";
            }
            newsService.delete(idNews);
            attr.addAttribute(deleteMessageAttribute, deleteMessage);
            return "redirect:/newsList";
        } catch (ServiceException e) {
            return "error";
        }
    }

}
