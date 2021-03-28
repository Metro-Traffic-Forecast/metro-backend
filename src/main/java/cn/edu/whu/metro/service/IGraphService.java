package cn.edu.whu.metro.service;

import cn.edu.whu.metro.graph.node.StationNode;

import java.util.HashSet;
import java.util.List;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:55
 **/
public interface IGraphService {

    /**
     * neo4j中创建数据
     * @author thomas
     * @since 1.0
     * @date 2021/3/27 22:56
     * @param
     * @return void
     **/
    void createData();

    /**
     * 根据id查询节点
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 0:52
     * @param
     * @return
     **/
    Iterable<StationNode> queryNodesByID(HashSet<String> id);



    /**
     * 在neo4j中创建站点网络
     * @author thomas
     * @since 1.0
     * @date 2021/3/28 0:06
     * @param
     * @return
     **/
    void createStationsGraph();
}
