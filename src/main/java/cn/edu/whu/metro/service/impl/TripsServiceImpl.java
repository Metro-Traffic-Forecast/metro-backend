package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.dto.StationFlowDTO;
import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.mapper.StationMapper;
import cn.edu.whu.metro.mapper.TripsMapper;
import cn.edu.whu.metro.service.ITripsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2021-03-26
 */
@Service
public class TripsServiceImpl extends ServiceImpl<TripsMapper, Trips> implements ITripsService {

    @Autowired
    TripsMapper tripsMapper;

    @Autowired
    StationMapper stationMapper;

    @Override
    public void queryStationFlowInTimeSlice(Timestamp start, Timestamp end) {
        List<Station> stations = stationMapper.selectList(new QueryWrapper<Station>().orderByAsc("station_id"));

        Instant startSecond = start.toInstant();
        Instant endSecond = end.toInstant();
        int step = 6;
        File file = new File("C:\\Users\\thomas\\Desktop\\trips\\" + "trips-" + step + ".txt");
        try {
            if (!file.exists()) {
                Boolean res = file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            while (startSecond.isBefore(endSecond)) {
                int[] inFlowValue = new int[164];
                int[] outFlowValue = new int[164];
                Instant tmp = startSecond.plusSeconds(step * 60 * 60);
                List<StationFlowDTO> inFlow = tripsMapper.queryStationInFlowInTimeSlice(Timestamp.from(startSecond).toString(), Timestamp.from(tmp).toString());
                List<StationFlowDTO> outFlow = tripsMapper.queryStationOutFlowInTimeSlice(Timestamp.from(startSecond).toString(), Timestamp.from(tmp).toString());
                try {
                    inFlow.forEach( o -> inFlowValue[o.getId() - 1] = o.getFlow());
                    outFlow.forEach( o -> outFlowValue[o.getId() - 1] = o.getFlow());
                }
                catch (NullPointerException e) {
                    // 说名trips中的站点不在station列表中 啥也不干
                }
                StringBuilder line = new StringBuilder().append(inFlowValue[0]).append(",").append(outFlowValue[0]);
                for (int i=1; i<164; i++) {
                    line.append(",").append(inFlowValue[i]).append(",").append(outFlowValue[i]);
                }
                writer.write(line.toString());
                writer.flush();
                writer.newLine();
                startSecond = tmp;
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }




    }
}
