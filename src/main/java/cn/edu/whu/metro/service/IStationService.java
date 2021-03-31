package cn.edu.whu.metro.service;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.dto.UserInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 19:02
 * TODO
 **/
public interface IStationService {

    /**
     * 根据两个站点id查询站点之间的平均时间
     * @author thomas
     * @since 1.0
     * @date 2021/3/26 19:00
     * @param station1 第一个站点的id
     * @param station2 第二个站点的id
     * @return java.lang.Double
     **/
    StatisticInfoDTO queryAverageTimeBetweenStations(String station1, String station2);

    /**
     * @Description: 根据开始时间和时间跨度统计各个车站进和出的人流量
     * @Author: MrWang98
     * @Date: 2021/3/31 11:36
     * @Param:
     * @param station station
     * @param startTime startTime
     * @param endTime endTime
     * @Return: java.util.Map<java.lang.String,java.util.List<cn.edu.whu.metro.dto.UserInfoDTO>>
     */
    Map<String,List<UserInfoDTO>> queryStationPeopleNumber(String station,String startTime, String endTime);

    /**
     * 查询某条线路的站点之间所需要的平均时间
     * @author thomas
     * @since 1.0
     * @date 2021/3/26 21:03
     * @param lineName 线路名
     * @return java.util.Map<java.lang.String,java.lang.Double>
     **/
    Map<Integer, StatisticInfoDTO> queryAverageTimeBetweenStationsByLine(String lineName);
}
