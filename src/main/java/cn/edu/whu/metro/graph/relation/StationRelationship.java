package cn.edu.whu.metro.graph.relation;

import cn.edu.whu.metro.graph.node.StationNode;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * 关系实体
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:46
 **/

@Data
@Builder
@RelationshipEntity(type = "next_to")
public class StationRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private StationNode self;

    @EndNode
    private StationNode neighbor;

    @Property
    private Double distance;

}
