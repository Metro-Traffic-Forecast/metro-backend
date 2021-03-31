package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author MrWang
 * @Date 2021/3/28 12:03
 */
@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String station;

    private Boolean sexual;

    private Double number;

    public UserInfoDTO(){
        sexual = null;
        number = 0.0;
    }
}
