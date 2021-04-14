package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.StatisticInfoDTO;
import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.service.IStationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "地铁站相关接口")
public class MetroStationController {

    @Autowired
    IStationService stationService;

    @GetMapping("/api/line")
    Map<Integer, StatisticInfoDTO> test(@RequestParam("lineName") String lineName) {
        return stationService.queryAverageTimeBetweenStationsByLine(lineName);
    }

    @GetMapping("/api/station")
    StatisticInfoDTO test2(@RequestParam("st1") String st1, @RequestParam("st2") String st2) {
        StatisticInfoDTO res = stationService.queryAverageTimeBetweenStations(st1, st2);
        return res;
    }



    @ApiOperation("查询所有站点的信息")
    @GetMapping("/metro/station")
    public List<Station> getStations() {
        return stationService.list(new QueryWrapper<Station>().orderByAsc("id"));
    }

}
