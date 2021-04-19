package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.StationTurnoverDTO;
import cn.edu.whu.metro.service.ITripsService;
import cn.edu.whu.metro.vo.LineSectionFlowVO;
import cn.edu.whu.metro.vo.StationSectionFlowVO;
import cn.edu.whu.metro.vo.StationFlowVO;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("查询某个时间段内所有站点的入站客流")
    @GetMapping("/metro/station/inflow")
    public List<StationFlowVO[]> queryStationInFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(value = "step", required = false) @ApiParam(value = "时间步长，单位小时;若小于等于0或不填，则返回整段时间的客流", example = "6") Integer step) {
        if (step == null || step <= 0) {
            return tripsService.queryStationInFlow(Timestamp.valueOf(start), Timestamp.valueOf(end));
        }
        return tripsService.queryStationInFlow(Timestamp.valueOf(start), Timestamp.valueOf(end), step);
    }

    @ApiOperation("查询某个时间段内所有站点的出站客流")
    @GetMapping("/metro/station/outflow")
    public List<StationFlowVO[]> queryStationOutFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(value = "step", required = false) @ApiParam(value = "时间步长，单位小时;若小于等于0或不填，则返回整段时间的客流", example = "6") Integer step) {
        if (step == null || step <= 0) {
            return tripsService.queryStationOutFlow(Timestamp.valueOf(start), Timestamp.valueOf(end));
        }
        return tripsService.queryStationOutFlow(Timestamp.valueOf(start), Timestamp.valueOf(end), step);
    }

    @ApiOperation("按站点查询某个时间段内某条线路的断面客流")
    @GetMapping("/metro/line/station/flow")
    public List<StationSectionFlowVO> queryStationSectionFlow(
            @RequestParam("step") @ApiParam(value = "线路名称", example = "1号线") String lineName,
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
            ) {

        return tripsService.queryStationSectionFlow(lineName, start, end);
    }

    @ApiOperation("按线路查询某个时间段内所有线路的断面客流")
    @GetMapping("/metro/line/flow")
    public List<LineSectionFlowVO> queryLineSectionFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) {

        return tripsService.queryLineSectionFlow(start, end);
    }

    @ApiOperation("查询某个时间段内所有线路的换乘客流")
    @GetMapping("/metro/line/exchange")
    public HashMap<String, HashMap<String, Integer>> queryLineExchangeFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
            ) {
        return tripsService.queryLineExchangeFlow(start, end);
    }


    @ApiOperation("查询某个时间段内所有站点的营业额")
    @GetMapping("/metro/station/turnover")
    public Map<String, Integer>queryStationTurnover(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam("end") @ApiParam(value = "结束时间", example = "2020-01-02 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end
    ) {
        return tripsService.queryStationTurnover(start, end);
    }


    @ApiOperation("查询预测客流")
    @GetMapping("/metro/prediction/flow")
    public String queryPredictionFlow(
            @RequestParam("start") @ApiParam(value = "开始时间", example = "2019-12-26 00:00:00") String start
    ) {
        //链式构建请求
        String result = HttpRequest.get("localhost:5000/predict?startTime=" + start)
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(JSONUtil.parseObj(result).toString());
        return JSONUtil.parseObj(result).toString();
    }

}
