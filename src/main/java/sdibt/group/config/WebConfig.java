package sdibt.group.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import sdibt.group.entity.Aliyun;
import sdibt.group.util.ApiInterceptor;
import sdibt.group.util.CustomRequestMappingHandlerMapping;
import sdibt.group.util.WebExceptionAspect;

import java.util.List;

/**
 * @author JacksonWang
 * @date 2019/3/28 10:51
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${appKey}")
    private String appKey;
    @Value("${appSecret}")
    private String appSecret;
    @Value("${bucket}")
    private String bucket;
    @Value("${endPoint}")
    private String endPoint;

    /**
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new ApiInterceptor());
    }

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        CustomRequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    /**
     *配置拦截器
     * @return
     */
    @Bean
    public ApiInterceptor interceptor() {
        return  new ApiInterceptor();
    }

    /**
     * 配置异常处理
     * @return
     */
    @Bean
    public WebExceptionAspect exceptionAspect() {
        return new WebExceptionAspect();
    }

    @Bean
    public Aliyun aliyun() {
        return Aliyun.options()
                .setAppKey(appKey)
                .setAppSecret(appSecret)
                .setBucket(bucket)
                .setEndPoint(endPoint)
                .build();
    }

    /**
     * 自定义JSON解析
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //定义一个converter转换消息对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fastjson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将converter添加到converters中
        converters.add(fastConverter);
    }
}
