package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.dto.StationFlowDTO;
import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.mapper.StationMapper;
import cn.edu.whu.metro.mapper.TripsMapper;
import cn.edu.whu.metro.service.ITripsService;
import cn.edu.whu.metro.vo.StationFlowVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

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
    public void queryStationFlowInTimeSlice(Timestamp start, Timestamp end, int step) {
        List<Station> stations = stationMapper.selectList(new QueryWrapper<Station>().orderByAsc("station_id"));

        Instant startSecond = start.toInstant();
        Instant endSecond = end.toInstant();

        File file = new File("C:\\Users\\thomas\\Desktop\\trips\\" + "trips-" + step + ".txt");
        try {
            if (!file.exists()) {
                Boolean res = file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            while (startSecond.isBefore(endSecond)) {
                Instant tmp = startSecond.plusSeconds(step * 60 * 60);
                Timestamp from = Timestamp.from(startSecond);
                Timestamp to = Timestamp.from(tmp);
                // 忽略每天凌晨的数据
                if (from.getHours() == 0) {
                    startSecond = tmp;
                    continue;
                }
                String time = String.valueOf(from.getYear() + 1900) + "年" + String.valueOf(from.getMonth() + 1) + "月" + String.valueOf(from.getDate()) + "日";
                int[] inFlowValue = new int[164];
                int[] outFlowValue = new int[164];
                List<StationFlowDTO> inFlow = tripsMapper.queryStationInFlowInTimeSlice(from.toString(), to.toString());
                List<StationFlowDTO> outFlow = tripsMapper.queryStationOutFlowInTimeSlice(from.toString(), to.toString());
                try {
                    inFlow.forEach( o -> inFlowValue[o.getId() - 1] = o.getFlow());
                    outFlow.forEach( o -> outFlowValue[o.getId() - 1] = o.getFlow());
                }
                catch (NullPointerException e) {
                    // 说名trips中的站点不在station列表中 啥也不干
                }
                StringBuilder line = new StringBuilder()
//                        .append(time)
//                        .append(",")
                        .append(inFlowValue[0])
//                        .append(",")
//                        .append(outFlowValue[0])
                        ;
                for (int i=1; i<164; i++) {
                    line
                            .append(",")
                            .append(inFlowValue[i])
//                            .append(",")
//                            .append(outFlowValue[i])
                    ;
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

    @Override
    public void queryLineFlowInTimeSlice(Timestamp start, Timestamp end, int step) {
        Instant startSecond = start.toInstant();
        Instant endSecond = end.toInstant();

        while (startSecond.isBefore(endSecond)) {
            Instant tmp = startSecond.plusSeconds(step * 60 * 60);
            Timestamp from = Timestamp.from(startSecond);
            Timestamp to = Timestamp.from(tmp);
            // 忽略每天凌晨的数据
//                if (from.getHours() == 0) {
//                    startSecond = tmp;
//                    continue;
//                }
            String time = String.valueOf(from.getYear() + 1900) + "年" + String.valueOf(from.getMonth() + 1) + "月" + String.valueOf(from.getDate()) + "日";
            HashMap<String, Integer> flow = new HashMap<>(8);
            tripsMapper.queryLineInFlowInTimeSlice(from.toString(), to.toString()).stream().forEach( o -> flow.put(o.getLineName(), o.getFlow()));
            flow.forEach((k, v) -> System.out.println(k + " " + v));

            startSecond = tmp;
        }



    }

    @Override
    public List<StationFlowVO[]> queryStationInFlow(Timestamp start, Timestamp end, int step) {
        List<StationFlowVO[]> result = new ArrayList<>();
        Instant startSecond = start.toInstant();
        Instant endSecond = end.toInstant();
        while (startSecond.isBefore(endSecond)) {
            Instant tmp = startSecond.plusSeconds(step * 60 * 60);
            Timestamp from = Timestamp.from(startSecond);
            Timestamp to = Timestamp.from(tmp);

            StationFlowVO[] stationFlow = new StationFlowVO[164];
            List<StationFlowVO> inFlow = tripsMapper.queryStationInFlow(from.toString(), to.toString());
            inFlow.forEach(o -> stationFlow[o.getId() - 1] = o);
            result.add(stationFlow);
            startSecond = tmp;
        }
        return result;
    }

    @Override
    public List<StationFlowVO[]>  queryStationOutFlow(Timestamp start, Timestamp end, int step) {
        return null;
    }
}
