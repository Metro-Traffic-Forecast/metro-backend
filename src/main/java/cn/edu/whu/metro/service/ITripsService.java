package cn.edu.whu.metro.service;

import cn.edu.whu.metro.entity.Trips;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Timestamp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
public interface ITripsService extends IService<Trips> {

    /**
     * 根据时间段查询客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    void queryStationFlowInTimeSlice(Timestamp start, Timestamp end, int step);

}
