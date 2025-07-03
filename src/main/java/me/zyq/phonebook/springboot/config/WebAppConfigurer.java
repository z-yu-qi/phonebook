package me.zyq.phonebook.springboot.config;

import me.zyq.phonebook.springboot.interceptor.SysInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截配置--调用链
 * @author djin
 * @create 2019-08-13 下午 12:26
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter{
    /**
     * 配置不需要拦截和需要拦截的请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置不被拦截的路径
        String[] patterns = new String[] {"/tadmin/login","/*.html","/image"};
        registry.addInterceptor(new SysInterceptor())
                .addPathPatterns("/**")  //配置拦截的路径
                .excludePathPatterns(patterns);  //剔除掉某些拦截的路径
    }
}
