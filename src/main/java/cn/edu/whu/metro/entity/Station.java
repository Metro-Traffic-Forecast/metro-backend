package cn.edu.whu.metro.entity;

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
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Station对象", description="")
public class Station implements Serializable {


    private String id;

    @TableId(value = "station_id", type = IdType.AUTO)
    private String stationId;

    private String lineName;

    private String district;

    private Integer sequence;

    private String stationName;


}
