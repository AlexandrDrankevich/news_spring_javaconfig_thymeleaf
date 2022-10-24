package by.htp.ex.service.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.entity.UserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    @Transactional
    public boolean registration(UserInfo user) throws ServiceException {
             try {
            return userDAO.registration(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
