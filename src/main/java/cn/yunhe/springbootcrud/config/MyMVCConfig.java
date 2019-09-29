package cn.yunhe.springbootcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//配置类 拓展
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {
    //扩展视图映射 和添加了一个controller一样的意思
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置视图控制器 路径是:   设置需要访问的资源的名字.(前后缀已经在webmvc自动配置类设置好了)
        registry.addViewController("/main").setViewName("dashboard");

    }
    //将对象放入容器 方法名和自动配置类的相同 如果容器中不存在下面创建的对象才会执行自动配置类的.显然,这里创建了,对象名相同,
    //默认的就不会运行了.根据自动配置类中的语言定义,配置文件等.修改中英文
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器对象,设置拦截的请求,设置放行的请求  后面是放行的请求, 前两个是首页,后面是登录.但是只有用户名密码正确才会登录上.如果直接访问/main
        //会判断session是否有用户信息.只有有信息才会允许访问.
        registry.addInterceptor(new MyLoginInterceptor()).addPathPatterns("/*").excludePathPatterns("/","/index","/login");
    }
}
