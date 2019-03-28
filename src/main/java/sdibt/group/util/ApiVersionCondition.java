package sdibt.group.util;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjisen
 * @date 2019/3/28 13:01
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    //路径中版本的前缀，这里用/v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN  = Pattern.compile("v(\\d+)/");
    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition condition) {
        //采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(condition.getApiVersion());
    }

    @Nullable
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher matcher = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (matcher.find()) {
            Integer version = Integer.valueOf(matcher.group(1));
            if (version >= this.apiVersion) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition condition, HttpServletRequest request) {
        //优先匹配最新的版本号
        return condition.getApiVersion()-this.apiVersion;
    }
}
