package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.mapper.StationMapper;
import cn.edu.whu.metro.mapper.TripsMapper;
import cn.edu.whu.metro.mapper.UsersMapper;
import cn.edu.whu.metro.service.IStationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 19:03
 * TODO
 **/
@Service
public class StationServiceImpl implements IStationService {

    private static long ONE_MINUTE = 60000L;

    @Autowired
    TripsMapper tripsMapper;

    @Autowired
    StationMapper stationMapper;

    @Autowired
    UsersMapper usersMapper;

    @Override
    public StatisticInfoDTO queryAverageTimeBetweenStations(String station1, String station2) {
        return tripsMapper.queryAverageTimeBetweenStations(station1, station2);
    }

    @Override
    public Map<String,List<UserInfoDTO>> queryStationPeopleNumber(String station,String startTime, String endTime){
        Map<String,List<UserInfoDTO>> result = new HashMap<>();

        if(station==""){
            result.put("in",usersMapper.countInBetween(startTime,endTime));
            result.put("out",usersMapper.countOutBetween(startTime,endTime));
        }
        else{
            result.put("in",usersMapper.stationCountInBetween(station,startTime,endTime));
            result.put("out",usersMapper.stationCountOutBetween(station,startTime,endTime));
        }
        return result;
    }

    @Override
    public Map<Integer, StatisticInfoDTO> queryAverageTimeBetweenStationsByLine(String lineName) {
        List<Station> data = stationMapper.selectList(new QueryWrapper<Station>().eq("line_name", lineName).orderByAsc("sequence"));
        Station[] stations = data.toArray(new Station[0]);
        Map<Integer, StatisticInfoDTO> result = new HashMap<>();
        for (int i=0; i<stations.length; i++) {
            if (i == stations.length-1) {
                result.put(stations[i].getSequence(), new StatisticInfoDTO());
            }
            else {
                result.put(stations[i].getSequence(), tripsMapper.queryAverageTimeBetweenStations(stations[i].getStationId(), stations[i+1].getStationId()));
            }
        }
        return result;
    }

}
