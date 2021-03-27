package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.service.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
