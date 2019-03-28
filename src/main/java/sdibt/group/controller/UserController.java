package sdibt.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sdibt.group.annotation.ApiVersion;
import sdibt.group.entity.User;
import sdibt.group.service.UserService;

import java.util.List;

/**
 * @author JacksonWang
 * @date 2019/3/27 15:11
 */
@RestController
@ApiVersion(1)
@RequestMapping("{version}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> findAll() {
        List<User> users = userService.findAll();
        System.out.println("UserController::findAll:users = "+users.size());
        return users;
    }
}
