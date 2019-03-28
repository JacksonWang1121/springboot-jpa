package sdibt.group.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 异常处理
 * @author wangjisen
 * @date 2019/3/28 11:44
 */
@Component
@Aspect
public class WebExceptionAspect implements ThrowsAdvice {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionAspect.class);

    //凡是注解了RequestMapping的方法都被拦截
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {
    }

    /**
     * 拦截web层异常，记录异常日志，并返回友好信息到前端
     * 目前只拦截Exception，是否要拦截Error需在做考虑
     * @param e 异常对象
     */
    @AfterThrowing(pointcut = "execution(* webPointcut())", throwing = "e")
    public void handleThrowing(Exception e) {
        e.printStackTrace();
        logger.error("发现异常！"+e.getMessage());
        logger.error(JSON.toJSONString(e.getStackTrace()));
        //这里输入友好性信息
        writeContent("出现异常");
    }

    @AfterThrowing(pointcut = "execution(* sdibt.group.*.*(..))", throwing = "e")
    public void afterThrowing(Exception e) throws Throwable {
        logger.debug("发现异常！"+e.getMessage());
        if (StringUtils.isNotBlank(e.getMessage())) {
            writeContent("出现异常的原因："+e.getMessage());
        } else {
            writeContent("参数错误！");
        }
    }

    /**
     * 将内容输出到浏览器
     * @param content 输出内容
     */
    public void writeContent(String content) {
        System.out.println("content = "+content);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","test/plain;charset=UTF-8");
        response.setHeader("icop-content-type","exception");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print((content==null)?"":content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    protected void validate(BindingResult result) {
        if (result.hasFieldErrors()) {
            List<FieldError> errorList = result.getFieldErrors();
            errorList.stream().forEach(item -> Assert.isTrue(false, item.getDefaultMessage()));
        }
    }

}
