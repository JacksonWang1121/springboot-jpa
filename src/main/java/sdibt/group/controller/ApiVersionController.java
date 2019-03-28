package sdibt.group.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdibt.group.annotation.ApiVersion;

/**
 * @author wangjisen
 * @date 2019/3/28 13:24
 */
@RestController
@ApiVersion(1)
@RequestMapping("{version}/dd")
public class ApiVersionController {
}
