package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.service.ITripsService;
import cn.edu.whu.metro.vo.StationFlowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;

/**
 * 地铁客流相关控制器
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/14 15:04
 **/
@RestController
@Api(tags = "客流相关接口")
public class MetroFlowController {

    @Autowired
    ITripsService tripsService;


    @ApiOperation("查询某个时间段内所有站点的客流")
    @GetMapping("/metro/inflow")
    public List<StationFlowVO[]> queryStationOutFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam("step") @ApiParam(value = "时间步长，单位小时", example = "6") Integer step) {
        return tripsService.queryStationInFlow(Timestamp.valueOf(start), Timestamp.valueOf(end), step);
    }

    @ApiOperation("查询某个时间段内所有线路的换乘客流")
    @GetMapping("/metro/line/exchange")
    public HashMap<String, HashMap<String, Integer>> queryLineExchangeFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
            ) {
        return tripsService.queryLineExchangeFlow(start, end);
    }

}
