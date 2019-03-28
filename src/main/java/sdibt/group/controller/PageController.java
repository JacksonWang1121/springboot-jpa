package sdibt.group.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangjisen
 * @date 2019/3/28 13:48
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/home")
    @ResponseBody
    public String home(String name) {
        return "Hi "+name+", I am from port: "+port;
    }

    @RequestMapping("/index")
    public String index() {
        int a = 10/0;
        return "index";
    }

}
