package by.htp.ex.dao.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.entity.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean registration(UserInfo user) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        try {
            if (isloginExist(currentSession, user.getLogin())) {
                return false;
            }
            currentSession.save(user);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return true;
    }


    private boolean isloginExist(Session currentSession, String login) {
        Query<UserInfo> query = currentSession.createQuery("from UserInfo v where v.login=:login",
                UserInfo.class);
        query.setParameter("login", login);
        return query.uniqueResult() != null;
    }
}
