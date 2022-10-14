package by.htp.ex.dao.impl;

import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAO implements INewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<News> theQuery = currentSession.createQuery("from News order by date desc", News.class);
            theQuery.setMaxResults(count);
            return theQuery.getResultList();
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public List<News> getList() throws NewsDAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            Query<News> theQuery = currentSession.createQuery("from News order by date desc", News.class);
            List<News> result = theQuery.getResultList();
            return result;
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public News fetchById(int id) throws NewsDAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.get(News.class, id);
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public void saveUpdateNews(News news) throws NewsDAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.saveOrUpdate(news);
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public void deleteNews(String[] idNews) throws NewsDAOException {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            for (String id : idNews) {
                Query theQuery = currentSession.createQuery("delete from News where idNews=:id");
                theQuery.setParameter("id", Integer.parseInt(id));
                theQuery.executeUpdate();
            }
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }
}
