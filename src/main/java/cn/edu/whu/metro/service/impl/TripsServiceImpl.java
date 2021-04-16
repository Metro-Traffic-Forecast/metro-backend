package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.dto.LineFlowDTO;
import cn.edu.whu.metro.dto.StationIdFlowDTO;
import cn.edu.whu.metro.dto.StationNameFlowDTO;
import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.entity.Trips;
import cn.edu.whu.metro.mapper.StationMapper;
import cn.edu.whu.metro.mapper.TripsMapper;
import cn.edu.whu.metro.service.ITripsService;
import cn.edu.whu.metro.vo.LineSectionFlowVO;
import cn.edu.whu.metro.vo.StationSectionFlowVO;
import cn.edu.whu.metro.vo.StationFlowVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import sun.security.util.ArrayUtil;

//import javax.sound.sampled.Line;
import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
                List<StationIdFlowDTO> inFlow = tripsMapper.queryStationInFlowInTimeSlice(from.toString(), to.toString());
                List<StationIdFlowDTO> outFlow = tripsMapper.queryStationOutFlowInTimeSlice(from.toString(), to.toString());
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
    public List<StationFlowVO[]> queryStationInFlow(Timestamp start, Timestamp end) {

        StationFlowVO[] stationFlow = new StationFlowVO[164];
        List<StationFlowVO> inFlow = tripsMapper.queryStationInFlow(start.toString(), end.toString());
        inFlow.forEach(o -> stationFlow[o.getId() - 1] = o);

        return new ArrayList<StationFlowVO[]>() {{ add(stationFlow); }};

    }

    @Override
    public List<StationFlowVO[]>  queryStationOutFlow(Timestamp start, Timestamp end, int step) {
        List<StationFlowVO[]> result = new ArrayList<>();
        Instant startSecond = start.toInstant();
        Instant endSecond = end.toInstant();
        while (startSecond.isBefore(endSecond)) {
            Instant tmp = startSecond.plusSeconds(step * 60 * 60);
            Timestamp from = Timestamp.from(startSecond);
            Timestamp to = Timestamp.from(tmp);

            StationFlowVO[] stationFlow = new StationFlowVO[164];
            List<StationFlowVO> inFlow = tripsMapper.queryStationOutFlow(from.toString(), to.toString());
            inFlow.forEach(o -> stationFlow[o.getId() - 1] = o);
            result.add(stationFlow);
            startSecond = tmp;
        }
        return result;
    }

    @Override
    public List<StationFlowVO[]> queryStationOutFlow(Timestamp start, Timestamp end) {
        StationFlowVO[] stationFlow = new StationFlowVO[164];
        List<StationFlowVO> outFlow = tripsMapper.queryStationOutFlow(start.toString(), end.toString());
        outFlow.forEach(o -> stationFlow[o.getId() - 1] = o);

        return new ArrayList<StationFlowVO[]>() {{ add(stationFlow); }};
    }

    @Override
    public HashMap<String, HashMap<String, Integer>> queryLineExchangeFlow(LocalDateTime start, LocalDateTime end) {
        HashMap<String, HashMap<String, Integer>> result = new HashMap<>(64);
        List<String> lineName = stationMapper.queryLineName();
        lineName.forEach(
            m -> {
                HashMap<String, Integer> map = new HashMap<>(1);
                lineName.forEach(
                    n -> {
                        if (!m.equals(n)) {
                            Integer count = tripsMapper.queryLineExchangeFlow(m, n, start.toString(), end.toString());
                            map.put(n, count);
                        }
                    }
                );
                result.put(m, map);
            }
        );
        return result;
    }

    @Override
    public List<StationSectionFlowVO> queryStationSectionFlow(String lineName, LocalDateTime start, LocalDateTime end) {
        // 首先查出所有站点的上行客流
        List<StationNameFlowDTO> upFlow = tripsMapper.queryStationInFlowByLine(lineName, start.toString(), end.toString());
        Map<String, Integer> upFlowCache = upFlow.stream().collect(Collectors.toMap(StationNameFlowDTO::getStationId, StationNameFlowDTO::getFlow));
        // 然后查出所有站点的下行客流
        List<StationNameFlowDTO> downFlow = tripsMapper.queryStationOutFlowByLine(lineName, start.toString(), end.toString());
        Map<String, Integer> downFlowCache = downFlow.stream().collect(Collectors.toMap(StationNameFlowDTO::getStationId, StationNameFlowDTO::getFlow));

        // 查出线路的所有站点，按sequence排序
        List<String> stations = stationMapper.selectList(new QueryWrapper<Station>().select("station_id").eq("line_name", lineName).orderByAsc("sequence")).stream().map(Station::getStationId).collect(Collectors.toList());
        // 构造返回结果
        return stations.stream().map(
            station -> new StationSectionFlowVO()
                .setStationId(station)
                .setInflow(upFlowCache.getOrDefault(station, 0))
                .setOutflow(downFlowCache.getOrDefault(station, 0))
        ).collect(Collectors.toList());
    }

    @Override
    public List<LineSectionFlowVO> queryLineSectionFlow(LocalDateTime start, LocalDateTime end) {
        // 入站出站客流都是按线路名字典序排序好的
        List<LineFlowDTO> inFlow = tripsMapper.queryLineInFlowInTimeSlice(start.toString(), end.toString());
        Map<String, Integer> upFlowCache = inFlow.stream().collect(Collectors.toMap(LineFlowDTO::getLineName, LineFlowDTO::getFlow));
        List<LineFlowDTO> outFlow = tripsMapper.queryLineOutFlowInTimeSlice(start.toString(), end.toString());
        Map<String, Integer> downFlowCache = outFlow.stream().collect(Collectors.toMap(LineFlowDTO::getLineName, LineFlowDTO::getFlow));

        List<String> line = stationMapper.queryLineName();
        return line.stream().map(
            l -> new LineSectionFlowVO()
                .setLineName(l)
                .setInflow(upFlowCache.getOrDefault(l, 0))
                .setOutflow(downFlowCache.getOrDefault(l, 0))
        ).collect(Collectors.toList());

    }
}
