package cn.edu.whu.metro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 18:39
 * spring boot 主程序
 **/
@SpringBootApplication
@MapperScan("cn.edu.whu.metro.mapper")
public class MetroApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetroApplication.class, args);
    }
}