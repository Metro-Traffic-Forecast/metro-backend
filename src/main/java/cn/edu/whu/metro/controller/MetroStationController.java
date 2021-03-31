package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.service.IStationService;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 18:45
 * 地铁站相关控制器
 **/
@RestController
public class MetroStationController {

    @Autowired
    IStationService stationService;

    @GetMapping("/line")
    Map<Integer, StatisticInfoDTO> test(@RequestParam("lineName") String lineName) {
        return stationService.queryAverageTimeBetweenStationsByLine(lineName);
    }

    @GetMapping("/station")
    StatisticInfoDTO test2(@RequestParam("st1") String st1, @RequestParam("st2") String st2) {
        StatisticInfoDTO res = stationService.queryAverageTimeBetweenStations(st1, st2);
        return res;
    }

    @GetMapping("/user")
    Map<String,List<UserInfoDTO>> test3(@RequestParam @Nullable String station, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        Map<String,List<UserInfoDTO>> result = stationService.queryStationPeopleNumber(station,startTime,endTime);
        return result;
    }

}
