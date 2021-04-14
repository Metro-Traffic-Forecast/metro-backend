package cn.edu.whu.metro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域配置
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/14 22:36
 **/
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS).maxAge(3600);
    }

}