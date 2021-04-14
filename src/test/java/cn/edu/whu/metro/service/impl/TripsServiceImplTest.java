package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.MetroApplicationTest;
import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.mapper.TripsMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 16:56
 **/
public class TripsServiceImplTest extends MetroApplicationTest {

    @Autowired
    TripsMapper tripsMapper;

    @Autowired
    TripsServiceImpl tripsService;

    @Test
    public void query() {
        IPage<Trips> page = new Page<>();
        tripsMapper.selectPage(page, null);
        System.out.println(page.getRecords().size());
    }

    @Test
    public void queryFlow() {
        tripsService.queryStationFlowInTimeSlice(new Timestamp(119, 11, 27, 0, 0, 0, 0), new Timestamp(120, 6, 17, 0, 0, 0, 0), 6);
    }

    @Test
    public void queryLineFlow() {
        tripsService.queryLineFlowInTimeSlice(new Timestamp(119, 11, 30, 0, 0, 0, 0), new Timestamp(120, 0, 20, 0, 0, 0, 0), 24);
    }
}
