package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.service.IStationService;
import cn.edu.whu.metro.vo.StationFlowVO;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/4/14 15:04
 **/
@RestController
@Api(tags = "用户相关接口")
public class MetroUserController {

    @Autowired
    IStationService stationService;

    @GetMapping("/metro/user")
    Map<String, List<UserInfoDTO>> test3(@RequestParam @Nullable String station, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime){
        Map<String, List<UserInfoDTO>> result = stationService.queryStationPeopleNumber(station,startTime,endTime);
        return result;
    }


}
