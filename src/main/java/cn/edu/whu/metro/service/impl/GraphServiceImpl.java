package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.entity.Station;
import cn.edu.whu.metro.entity.StationGps;
import cn.edu.whu.metro.graph.node.StationNode;
import cn.edu.whu.metro.graph.relation.StationRelationship;
import cn.edu.whu.metro.mapper.StationGpsMapper;
import cn.edu.whu.metro.mapper.StationMapper;
import cn.edu.whu.metro.repository.StationNodeRepository;
import cn.edu.whu.metro.repository.StationRelationshipRepository;
import cn.edu.whu.metro.service.IGraphService;
import cn.edu.whu.metro.util.GeoUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:56
 **/
@Service
@Slf4j
public class GraphServiceImpl implements IGraphService {

    @Autowired
    StationMapper stationMapper;

    @Autowired
    StationNodeRepository stationNodeRepository;

    @Autowired
    StationRelationshipRepository stationRelationshipRepository;

    @Autowired
    StationGpsMapper stationGpsMapper;

    @Override
    public void createData() {
        StationNode node1 = StationNode.builder().stationId("Sta1").sequence(1).stationName("站点1").build();
        StationNode node2 = StationNode.builder().stationId("Sta2").sequence(2).stationName("站点2").build();
        node1.addLabel("标签1");
        stationNodeRepository.saveAll(new ArrayList<StationNode>() { { add(node1); add(node2); } });
        createRelationshipBetweenStations(node1, node2);
    }

    @Override
    public Iterable<StationNode> queryNodesByID(HashSet<String> id) {
        Iterable<StationNode> nodes = stationNodeRepository.findAllById(id);
        return nodes;
    }

    @Override
    public void createStationsGraph() {
        List<Object> lines = stationMapper.selectObjs(new QueryWrapper<Station>().select("distinct line_name").groupBy("line_name"));
        lines.forEach(
            line -> {
                List<Station> stations = stationMapper.selectList(new QueryWrapper<Station>().eq("line_name", line).orderByAsc("sequence"));
                for (int i=0; i<stations.size()-1; i++) {
                    Station station1 = stations.get(i);
                    Station station2 = stations.get(i+1);
                    StationNode first = StationNode.builder()
                            .stationId(station1.getStationId())
                            .sequence(station1.getSequence())
                            .stationName(station1.getStationName())
                            .labels(new ArrayList<String>(2) {{ add((String) line); add(station1.getDistrict());}})
                            .build();
                    StationNode second = StationNode.builder()
                            .stationId(station2.getStationId())
                            .sequence(station2.getSequence())
                            .stationName(station2.getStationName())
                            .labels(new ArrayList<String>(2) {{ add((String) line); add(station2.getDistrict());}})
                            .build();

                    stationNodeRepository.saveAll(new ArrayList<StationNode>() {{ add(first); add(second); }});
                    createRelationshipBetweenStations(first, second);
                }

            }
        );

        // 一号线
        // 沙坪坝
        linkLines("Sta89", "Sta136", "Sta137");
        // 大坪
        linkLines("Sta47", "Sta108", "Sta159");


        // 二号线
        // 谢家湾
        linkLines("Sta127", "Sta60", "Sta91");
        // 较场口
        linkLines("Sta129", "Sta1"); // 连接七星岗
        linkLines("Sta129", "Sta63"); // 连接小什字

        // 三号线
        // 鱼洞
        linkLines("Sta51", "Sta155");
        // 四公里
        linkLines("Sta41", "Sta32", "Sta116");
        // 两路口
        linkLines("Sta5", "Sta1", "Sta159");
        // 牛角沱
        linkLines("Sta98", "Sta79", "Sta78");
        // 碧津
        linkLines("Sta109", "Sta87");

        // 四号线
        // 重庆北站南广场
        linkLines("Sta135", "Sta95", "Sta90");
        // 民安大道
        linkLines("Sta84", "Sta135", "Sta90");

        // 六号线
        // 冉家坝
        linkLines("Sta23", "Sta26", "Sta17"); // 嵌入到环线中
        linkLines("Sta23", "Sta54", "Sta69"); // 嵌入到环线中
        // 大龙山
        linkLines("Sta56", "Sta23", "Sta54"); // 嵌入到环线中
        // 红旗河沟
        linkLines("Sta115", "Sta126", "Sta40"); // 嵌入到3号线
        // 五里店
        linkLines("Sta15", "Sta72", "Sta95"); // 嵌入到环线中
        // 上新街
        linkLines("Sta3", "Sta92", "Sta93"); // 嵌入到环线中

        // 十号线
        // 重庆北站北广场
        linkLines("Sta134", "Sta84", "Sta59");
        // 红土地
        linkLines("Sta114", "Sta162", "Sta15");
    }

    public void linkLines(String id1, String id2, String id3) {
        Optional<StationNode> node1 = stationNodeRepository.findById(id1);
        Optional<StationNode> node2 = stationNodeRepository.findById(id2);
        Optional<StationNode> node3 = stationNodeRepository.findById(id3);
        deleteRelations(id2, id3);
        createRelationshipBetweenStations(node1.get(), node2.get());
        createRelationshipBetweenStations(node1.get(), node3.get());
    }

    public void linkLines(String id1, String id2) {
        Optional<StationNode> node1 = stationNodeRepository.findById(id1);
        Optional<StationNode> node2 = stationNodeRepository.findById(id2);
        createRelationshipBetweenStations(node1.get(), node2.get());
    }

    public void createRelationshipBetweenStations(StationNode st1, StationNode st2) {
        List<StationGps> gps1 = stationGpsMapper.selectList(new QueryWrapper<StationGps>().select().eq("station", st1.getStationName()));
        List<StationGps> gps2 = stationGpsMapper.selectList(new QueryWrapper<StationGps>().select().eq("station", st2.getStationName()));
        try {
            Assert.isTrue(gps1.size() == 1);
            Assert.isTrue(gps2.size() == 1);
        }
        catch (IllegalArgumentException ee) {
            ee.printStackTrace();
            log.error(st1.getStationName() + " " + st2.getStationName());
        }


        Double distance = GeoUtil.getDistanceUseGeodesy(
                gps1.get(0).getWgs84X().doubleValue(), gps1.get(0).getWgs84Y().doubleValue(),
                gps2.get(0).getWgs84X().doubleValue(), gps2.get(0).getWgs84Y().doubleValue()
        );
        List<StationRelationship> oldRelations = stationRelationshipRepository.findStationRelationshipById(st1.getStationId(), st2.getStationId());
        if (oldRelations.size() != 0) {
            oldRelations.forEach( r -> stationRelationshipRepository.deleteById(r.getId()) );
        }
        stationRelationshipRepository.saveAll(
                new ArrayList<StationRelationship>() {
                    {
                        add(StationRelationship.builder().self(st1).neighbor(st2).distance(distance).build());
                        add(StationRelationship.builder().self(st2).neighbor(st1).distance(distance).build());
                    }
                }
        );
    }

    public void deleteRelations(String station1, String station2) {
        stationRelationshipRepository.deleteStationRelationshipById(station1, station2);
    }

    public int[][] getAdjacencyMatrix() {
        int[][] adjMatrix = new int[164][164];
        double[][] disMatrix = new double[164][164];
        File adjFile = new File("C:\\Users\\thomas\\Desktop\\trips\\" + "adj-matrix" + ".txt");
        File disFile = new File("C:\\Users\\thomas\\Desktop\\trips\\" + "dis-matrix" + ".txt");
        try {
            if (!adjFile.exists()) {
                Boolean res = adjFile.createNewFile();
            }
            if (!disFile.exists()) {
                Boolean res = disFile.createNewFile();
            }
            BufferedWriter adjWriter = new BufferedWriter(new FileWriter(adjFile));
            BufferedWriter disWriter = new BufferedWriter(new FileWriter(disFile));
            // List<Station> stations = stationMapper.selectList(new QueryWrapper<Station>().orderByAsc("line_name").orderByAsc("sequence"));
            List<Station> stations = stationMapper.selectList(new QueryWrapper<Station>().orderByAsc("id"));
            int size = stations.size();
            for (int i=0; i<size; i++) {
                for (int j=i+1; j<size; j++) {
                    List<StationRelationship> relationships = stationRelationshipRepository.findStationRelationshipById(stations.get(i).getStationId(), stations.get(j).getStationId());
                    if (relationships.size()!=0) {
                        adjMatrix[i][j] = 1;
                        adjMatrix[j][i] = 1;
                        disMatrix[i][j] = relationships.get(0).getDistance();
                        disMatrix[j][i] = disMatrix[i][j];
                    }
                }
            }
            Arrays.stream(adjMatrix).forEach( line -> {
                try {
                    adjWriter.write(Arrays.toString(line).replace("[", "").replace("]", "").replace(" ", ""));
                    adjWriter.newLine();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Arrays.stream(disMatrix).forEach( line -> {
                try {
                    disWriter.write(Arrays.toString(line).replace("[", "").replace("]", "").replace(" ", ""));
                    disWriter.newLine();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
            adjWriter.flush();adjWriter.close();
            disWriter.flush();disWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return  adjMatrix;
    }

    public double[][] getDistanceMatrix() {
        return null;
    }

}
