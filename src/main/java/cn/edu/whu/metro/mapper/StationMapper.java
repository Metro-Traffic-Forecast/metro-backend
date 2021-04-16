package cn.edu.whu.metro.mapper;

import cn.edu.whu.metro.entity.Station;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
public interface StationMapper extends BaseMapper<Station> {

    /**
     * 查询线路名称
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param
     * @return
     **/
    List<String> queryLineName();

    List<String> queryStationName();

}
