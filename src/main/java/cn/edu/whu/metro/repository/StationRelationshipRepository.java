package cn.edu.whu.metro.repository;

import cn.edu.whu.metro.graph.relation.StationRelationship;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:52
 **/
@Repository
public interface StationRelationshipRepository extends Neo4jRepository<StationRelationship, Long> {

    @Query("MATCH (startNode:StationNode)-[rel]-(endNode:StationNode) WHERE startNode.station_id = $0 AND endNode.station_id = $1 DELETE rel")
    void deleteStationRelationshipById(String node1, String node2);

    @Query("MATCH (f:StationNode{station_id: $0})-[r]->(t:StationNode{station_id: $1}) RETURN f,r,t")
    List<StationRelationship> findStationDirectedRelationshipById(String from, String to);

    @Query("MATCH (f:StationNode{station_id: $0})-[r]-(t:StationNode{station_id: $1}) RETURN f,r,t")
    List<StationRelationship> findStationRelationshipById(String from, String to);

    @Query("MATCH p=shortestPath((a:StationNode{station_id:$0})-[*]-(b:StationNode{station_id:$1})) RETURN p")
    List<StationRelationship> findDistanceBetweenStations(String from, String to);

}
