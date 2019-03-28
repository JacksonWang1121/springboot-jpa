package sdibt.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sdibt.group.entity.Aliyun;

/**
 * @author wangjisen
 * @date 2019/3/28 11:30
 */
@Controller
@RequestMapping("/aliyun")
public class AliyunController {

    @Autowired
    private Aliyun aliyun;

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "Aliyun OSS: appKey = "+aliyun.getAppKey()
                +", appSecret = "+aliyun.getAppSecret()
                +", bucket = "+aliyun.getBucket()
                +", endPoint = "+aliyun.getEndPoint();
    }

}
