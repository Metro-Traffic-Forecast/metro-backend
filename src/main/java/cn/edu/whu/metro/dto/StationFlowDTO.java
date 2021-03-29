package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.MapKey;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 21:15
 **/
@Data
@AllArgsConstructor
@Builder
public class StationFlowDTO {

    private Integer id;

    private Integer flow;

}
