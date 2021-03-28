package cn.edu.whu.metro.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author thomas
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StationGps对象", description="")
public class StationGps implements Serializable {


    @ApiModelProperty(value = "站点名称")
    @TableId(value = "station", type = IdType.AUTO)
    private String station;

    @ApiModelProperty(value = "区域编号")
    private String adcode;

    @ApiModelProperty(value = "区域名称")
    private String adname;

    private String gridcode;

    private BigDecimal x;

    private BigDecimal y;

    private BigDecimal wgs84X;

    private BigDecimal wgs84Y;


}
