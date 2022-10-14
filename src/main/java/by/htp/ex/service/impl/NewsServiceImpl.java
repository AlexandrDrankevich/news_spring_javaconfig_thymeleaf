package by.htp.ex.service.impl;


import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private INewsDAO newsDAO;

    @Override
    @Transactional
    public List<News> latestList(int count) throws ServiceException {
        try {
            List<News> latestNews = newsDAO.getLatestsList(count);
            if (latestNews.isEmpty()) {
                return null;
            }
            return latestNews;
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<News> list(Integer pageNumber, String newsCountOnPage) throws ServiceException {
        String newsCount = checkNewsCount(newsCountOnPage);
        try {
            List<News> allNewsList = newsDAO.getList();
            return getNewsOnPage(allNewsList, pageNumber, newsCount);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public News findById(int id) throws ServiceException {
        try {
            return newsDAO.fetchById(id);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void save(News news) throws ServiceException {
        try {
            newsDAO.saveUpdateNews(news);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<Integer> getPageCount(String newsCountOnPage) throws ServiceException {
        String newsCount = checkNewsCount(newsCountOnPage);
        try {
            return createPageCountList(newsDAO.getList(), newsCount);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void delete(String[] idNews) throws ServiceException {
        try {
            newsDAO.deleteNews(idNews);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }


    private List<Integer> createPageCountList(List<News> allNewsList, String newsCount) {
        double numberNews = Double.parseDouble(newsCount);
        int number = (int) (Math.ceil(allNewsList.size() / numberNews));
        List<Integer> pageCount = new ArrayList<Integer>();
        for (int i = 1; i <= number; i++) {
            pageCount.add(i);
        }
        return pageCount;
    }

    private List<News> getNewsOnPage(List<News> allNewsList, Integer pageNumber, String newsCount) {
        int numberNews = Integer.valueOf(newsCount);
        List<News> newsListOnPage = new ArrayList<News>();
        if (allNewsList.isEmpty()) {
            return null;
        }
        int startNews = pageNumber * numberNews - numberNews;
        int finishNews = pageNumber * numberNews;
        if (finishNews > allNewsList.size()) {
            finishNews = allNewsList.size();
        }
        for (int i = startNews; i < finishNews; i++) {
            newsListOnPage.add(allNewsList.get(i));
        }
        if (newsListOnPage.isEmpty() && pageNumber > 1) {
            return getNewsOnPage(allNewsList, pageNumber - 1, newsCount);
        }
        return newsListOnPage;

    }

    private String checkNewsCount(String newsCountOnPage) {
        String newsCount = "5";
        if (newsCountOnPage != null) {
            newsCount = newsCountOnPage;
        }
        return newsCount;
    }

}