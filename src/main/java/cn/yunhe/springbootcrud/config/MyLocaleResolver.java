package cn.yunhe.springbootcrud.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
//因为默认方法无法进行语言转换 (默认接收浏览器的语言请求 一直是中文.)所以重写方法
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        //获取可以修改语言的默认的对象
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            //如果参数不为空
            String[] split = l.split("_");
            //赋值 zh CN 或者 en  Us
            locale = new Locale(split[0],split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
