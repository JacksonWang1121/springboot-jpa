package sdibt.group.service;

import sdibt.group.entity.User;

import java.util.List;

/**
 * @author JacksonWang
 * @date 2019/3/27 15:07
 */
public interface UserService {
    List<User> findAll();
}
