package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点营业额统计
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/16 23:55
 **/

@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class StationTurnoverDTO {

    private String stationId;

    private Integer turnover;

}
