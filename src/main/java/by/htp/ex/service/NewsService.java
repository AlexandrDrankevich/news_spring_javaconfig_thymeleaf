package by.htp.ex.service;

import by.htp.ex.entity.News;

import java.util.List;

public interface NewsService {
    void save(News news) throws ServiceException;

    void delete(String[] idNews) throws ServiceException;

    List<News> latestList(int count) throws ServiceException;

    List<News> list(Integer pageNumber, String newsCount) throws ServiceException;

    News findById(int id) throws ServiceException;

    List<Integer> getPageCount(String newsCount) throws ServiceException;
}
