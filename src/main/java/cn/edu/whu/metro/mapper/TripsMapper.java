package cn.edu.whu.metro.mapper;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.entity.Trips;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
public interface TripsMapper extends BaseMapper<Trips> {

    /**
     * 根据两个站点id查询站点之间的平均时间
     * @author thomas
     * @since 1.0
     * @date 2021/3/26 19:00
     * @param station1 第一个站点的id
     * @param station2 第二个站点的id
     * @return java.lang.Double
     **/
    StatisticInfoDTO queryAverageTimeBetweenStations(@Param("station1") String station1, @Param("station2") String station2);

}
