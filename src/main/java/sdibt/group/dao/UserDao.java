package sdibt.group.dao;

import  sdibt.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author JacksonWang
 * @date 2019/3/27 15:05
 */
public interface UserDao extends JpaRepository<User, Long> {

}
