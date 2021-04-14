package cn.edu.whu.metro.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/1/21 23:46
 * 全局时间格式化，使得前端传LocalDateTime、LocalDate等类型的参数时，规定其格式
 **/
@Configuration
public class DateFormatterConfig {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * author LXP
     * description String->LocalDate
     */
    @Bean
    @ConditionalOnBean(name = "requestMappingHandlerAdapter")
    public Converter<String, LocalDate> localDateConverter() {
        return source -> {
            if (source.trim().length() == 0)
                return null;
            try {
                return LocalDate.parse(source);
            } catch (Exception e) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_PATTERN));
            }
        };
    }

    /**
     * author LXP
     * description String->LocalDateTime
     */
    @Bean
    @ConditionalOnBean(name = "requestMappingHandlerAdapter")
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return source -> {
            if (source.trim().length() == 0)
                return null;
            // 先尝试ISO格式: 2019-07-15T16:00:00
            try {
                return LocalDateTime.parse(source);
            } catch (Exception e) {
                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
            }
        };
    }

    /**
     * 统一配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        JavaTimeModule module = new JavaTimeModule();
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        module.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return builder -> {
            builder.simpleDateFormat(DATE_TIME_PATTERN);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
            builder.modules(module);
        };
    }


}
