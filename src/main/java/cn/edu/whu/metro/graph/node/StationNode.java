package cn.edu.whu.metro.graph.node;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点节点
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/27 22:33
 **/

@Getter
@Builder
@NodeEntity
public class StationNode {

    @Id
    @Property(name = "station_id")
    private String stationId;

    @Property(name = "station_name")
    private String stationName;

    @Property(name = "sequence")
    private Integer sequence;

    @Labels
    private List<String> labels;


    public void addLabel(String label) {
        if (this.labels == null) {
            this.labels = new ArrayList<>(4);
        }
        this.labels.add(label);
    }

}
