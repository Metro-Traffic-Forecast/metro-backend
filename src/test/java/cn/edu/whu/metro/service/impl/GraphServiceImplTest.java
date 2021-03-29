package cn.edu.whu.metro.service.impl;

import cn.edu.whu.metro.MetroApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 8:59
 **/

public class GraphServiceImplTest extends MetroApplicationTest {

    @Autowired
    private GraphServiceImpl graphService;

    @Test
    public void createData() {
        graphService.createStationsGraph();
    }

    @Test
    public void deleteRelationship() {
        graphService.deleteRelations("Sta137", "Sta136");
    }

    @Test
    public void getMatrices() {
        Arrays.stream(graphService.getAdjacencyMatrix()).forEach(row -> { Arrays.stream(row).forEach(System.out::print); System.out.println(); });
        graphService.getDistanceMatrix();
    }
}
