package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 10:05
 **/
@Data
@AllArgsConstructor
public class StatisticInfoDTO {

    private Double average;

    private Double standError;

    public StatisticInfoDTO() {
        average = 0.0;
        standError = 0.0;
    }
}
