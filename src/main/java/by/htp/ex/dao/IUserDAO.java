package by.htp.ex.dao;

import by.htp.ex.entity.UserInfo;

public interface IUserDAO {

    boolean registration(UserInfo user) throws DaoException;
}
