package cn.edu.whu.metro.service;

import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.vo.LineSectionFlowVO;
import cn.edu.whu.metro.vo.StationSectionFlowVO;
import cn.edu.whu.metro.vo.StationFlowVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
     * 根据时间段查询所有站点的入站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    void queryStationFlowInTimeSlice(Timestamp start, Timestamp end, int step);

    /**
     * 根据时间段查询线路客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    void queryLineFlowInTimeSlice(Timestamp start, Timestamp end, int step);

    /**
     * 根据时间段查询所有站点的入站客流, 有时间步长
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    List<StationFlowVO[]> queryStationInFlow(Timestamp start, Timestamp end, int step);

    /**
     * 根据时间段查询所有站点的入站客流， 无时间步长
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    List<StationFlowVO[]> queryStationInFlow(Timestamp start, Timestamp end);

    /**
     * 根据时间段查询所有站点的出站客流，有时间步长
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    List<StationFlowVO[]> queryStationOutFlow(Timestamp start, Timestamp end, int step);

    /**
     * 根据时间段查询所有站点的出站客流， 无时间步长
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    List<StationFlowVO[]> queryStationOutFlow(Timestamp start, Timestamp end);

    /**
     * 根据时间段查询换乘客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    HashMap<String, HashMap<String, Integer>> queryLineExchangeFlow(LocalDateTime start, LocalDateTime end);


    /**
     * 根据时间段按站点查询线路断面客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 22:01
     * @param start 开始时间
     * @param end 结束时间
     * @return void
     **/
    List<StationSectionFlowVO> queryStationSectionFlow(String lineName, LocalDateTime start, LocalDateTime end);



    List<LineSectionFlowVO> queryLineSectionFlow(String lineName,  LocalDateTime start, LocalDateTime end);
}
