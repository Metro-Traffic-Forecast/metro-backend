package cn.edu.whu.metro.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 相关配置
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 17:06
 **/
@Configuration
@MapperScan("cn.edu.whu.metro.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
