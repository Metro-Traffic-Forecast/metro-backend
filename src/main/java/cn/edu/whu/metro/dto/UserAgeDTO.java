package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author MrWang98
 * @Date 2021/4/16 14:53
 **/
@Data
@AllArgsConstructor
public class UserAgeDTO {
    private Integer birth;
    private Integer number;
}
