package cn.edu.whu.metro.repository;

import cn.edu.whu.metro.graph.node.StationNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:51
 **/
@Repository
public interface StationNodeRepository extends Neo4jRepository<StationNode, String> {
}
