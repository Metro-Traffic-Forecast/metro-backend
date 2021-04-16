package cn.edu.whu.metro.controller;

import cn.edu.whu.metro.dto.UserInfoDTO;
import cn.edu.whu.metro.dto.UsersProvinceDTO;
import cn.edu.whu.metro.service.IStationService;
import cn.edu.whu.metro.service.IUsersService;
import cn.edu.whu.metro.vo.StationFlowVO;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @Autowired
    IUsersService usersService;

    @ApiOperation("根据时间查询站点的进出站总人数")
    @GetMapping("/metro/getNumberByStationAndTime")
    Map<String, List<UserInfoDTO>> test3(@RequestParam @Nullable String station,
                                         @RequestParam("startTime") @ApiParam(value = "开始时间", example = "2020-01-01 00:00:00") String startTime,
                                         @RequestParam("endTime") @ApiParam(value = "结束时间", example ="2020-01-01 00:00:00") String endTime){
        Map<String, List<UserInfoDTO>> result = stationService.queryStationPeopleNumber(station,startTime,endTime);
        return result;
    }

    @ApiOperation("根据站点id查询男女比例")
    @GetMapping("/metro/station/sexRatio")
    Map<String,Float> getSexRatio(@RequestParam @ApiParam(value = "站点id", example = "Sta1") String station, @RequestParam @ApiParam(value = "true返回人数；false表示返回比例", example = "true") Boolean type){
        Map<String,Float> result;
        if (type) {
            result = usersService.ratioOfSexualI(station);
        } else {
            result = usersService.ratioOfSexual(station);
        }

        return result;
    }

    @ApiOperation("根据站点id查询年龄比例")
    @GetMapping("/metro/station/ageRatio")
    List<Map<Integer,Float>> getAgeRatio(@RequestParam @ApiParam(value = "站点id", example = "Sta1") String station, @ApiParam(value = "true返回人数；false表示返回比例", example = "true") @RequestParam Boolean type){
        List<Map<Integer, Float>> result;
        if (type){
            result = usersService.ratioOfAgeI(station);
        }
        else {
            result = usersService.ratioOfAge(station);
        }

        return result;
    }

    Map<String,List<Map<Integer,Float>>> getAgeRatioOfAllStation(@RequestParam Boolean type){
        return null;
    }



    @ApiOperation("查询用户省份统计")
    @GetMapping("/metro/user/province")
    List<UsersProvinceDTO> queryUsersProvince() {
        return usersService.queryUsersProvince();
    }


}
