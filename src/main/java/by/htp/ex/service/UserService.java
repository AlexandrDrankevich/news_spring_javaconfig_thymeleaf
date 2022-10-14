package by.htp.ex.service;

import by.htp.ex.entity.UserInfo;

public interface UserService {

    boolean registration(UserInfo user) throws ServiceException;
}
