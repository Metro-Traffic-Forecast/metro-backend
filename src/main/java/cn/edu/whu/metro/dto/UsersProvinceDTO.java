package cn.edu.whu.metro.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/17 0:54
 **/

@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UsersProvinceDTO {

    private String provinceName;

    private Integer provinceId;

    private Integer count;

}
