package cn.edu.whu.metro.mapper;

import cn.edu.whu.metro.dto.*;
import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.vo.StationFlowVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询一个时间段内各个站点的进站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationIdFlowDTO> queryStationInFlowInTimeSlice(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询一个时间段内各个站点的出站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationIdFlowDTO> queryStationOutFlowInTimeSlice(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询一个时间段内各个站点的进站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationFlowVO> queryStationInFlow(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询一个时间段内各个站点的出站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationFlowVO> queryStationOutFlow(@Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 查询一个时间段内各个线路的入站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<LineFlowDTO> queryLineInFlowInTimeSlice(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询一个时间段内各个线路的出站客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<LineFlowDTO> queryLineOutFlowInTimeSlice(@Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 查询一个时间段内各个线路的换乘客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    Integer queryLineExchangeFlow(@Param("lineIn") String lineIn, @Param("lineOut") String lineOut, @Param("startTime") String startTime, @Param("endTime") String endTime);


    /**
     * 查询一个时间段内某条线路各个站点的上行客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationNameFlowDTO> queryStationInFlowByLine(@Param("lineName") String lineName, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询一个时间段内某条线路各个站点的下行客流
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationNameFlowDTO> queryStationOutFlowByLine(@Param("lineName") String lineName, @Param("startTime") String startTime, @Param("endTime") String endTime);



    /**
     * 查询一个时间段内所有站点的营业额
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 21:22
     * @param startTime 起始时间
     * @param endTime 结束时间
     **/
    List<StationTurnoverDTO> queryStationTurnover(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
