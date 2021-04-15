package cn.edu.whu.metro.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 描述线路的上下行客流
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/15 8:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ApiModel(description = "线路断面客流对象")
public class LineSectionFlowVO {

    @ApiModelProperty("线路名")
    private String lineName;

    @ApiModelProperty("上行客流")
    private Integer inflow;

    @ApiModelProperty("下行客流")
    private Integer outflow;

}
