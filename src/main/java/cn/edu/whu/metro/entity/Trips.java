package cn.edu.whu.metro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;

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
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Trips对象", description="")
public class Trips implements Serializable {


    @ApiModelProperty(value = "数据id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "入站名称")
    private String stationIn;

    @ApiModelProperty(value = "入站时间")
    private Timestamp timeIn;

    @ApiModelProperty(value = "出站名称")
    private String stationOut;

    @ApiModelProperty(value = "出站时间")
    private Timestamp timeOut;

    @ApiModelProperty(value = "售票渠道")
    private Integer approachNum;

    @ApiModelProperty(value = "售票价格")
    private String price;

    @ApiModelProperty(value = "出入站时间差（分钟）")
    private Integer duration;


}
