package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/14 23:53
 **/
@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class StationNameFlowDTO {

    private String stationId;

    private Integer flow;

}
