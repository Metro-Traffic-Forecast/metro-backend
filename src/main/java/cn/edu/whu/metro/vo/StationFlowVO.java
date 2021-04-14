package cn.edu.whu.metro.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/14 15:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "站点客流VO")
public class StationFlowVO {

    @ApiModelProperty("数据库id")
    private Integer id;

    @ApiModelProperty("站点id")
    private String stationId;

    @ApiModelProperty("客流")
    private Integer flow;

}
