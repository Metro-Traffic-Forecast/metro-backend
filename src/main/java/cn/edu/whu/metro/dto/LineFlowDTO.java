package cn.edu.whu.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/5 12:17
 **/
@Data
@AllArgsConstructor
@Builder
public class LineFlowDTO {

    private String lineName;

    private Integer flow;

}
