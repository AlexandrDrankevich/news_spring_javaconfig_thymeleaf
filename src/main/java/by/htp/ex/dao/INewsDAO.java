package by.htp.ex.dao;

import by.htp.ex.entity.News;

import java.util.List;

public interface INewsDAO {
    List<News> getList() throws NewsDAOException;

    List<News> getLatestsList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    void saveUpdateNews(News news) throws NewsDAOException;

    void deleteNews(String[] idNews) throws NewsDAOException;
}
