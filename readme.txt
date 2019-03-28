springboot2.1.3整合jpa

添加依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>




Git上传项目提示Push rejected: Push to origin/master was rejected解决办法
首先是你的项目中有和和历史不符的东西
Push rejected: Push to origin/master was rejected
推拒绝：推送到起源/主人被拒绝
直接是解决办法，直接打开你要上传代码的文件夹位置鼠标右键git Bash Here然后直接下面两行命令解决问题
git pull origin master –allow-unrelated-histories
git push -u origin master -f




springboot进阶

    1、yaml/properties文件
    2、多环境配置
    3、常用注解
        @springbootApplication
            = @springbootConfiguration + @EnableAutoConfiguration + @ComponentScan
                 springboot的配置注解              自动配置              扫描Bean
        @configuration    加入这个注解的类被认为是springboot的配置类
        @Bean    主要添加在@configuration或@springbootConfiguration注解的类
        @Value    定义全局变量（通常用public static去定义）
            好处：
                1、定义在配置文件里，变量发生变化，无须修改代码
                2、变量交给spring管理，性能更好
    4、注入任何类   假设：使用阿里云的 OSS 进行文件上传
    5、拦截器interceptor
    6、异常处理  对报错进行拦截  ------------------------->待实现
    7、优雅的输入合法性校验
    8、接口版本控制
    9、自定义JSON解析
    10、单元测试
    11、模板引擎