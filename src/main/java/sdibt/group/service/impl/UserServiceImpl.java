package sdibt.group.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdibt.group.dao.UserDao;
import sdibt.group.entity.User;
import sdibt.group.service.UserService;

import java.util.List;

/**
 * @author JacksonWang
 * @date 2019/3/27 15:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
