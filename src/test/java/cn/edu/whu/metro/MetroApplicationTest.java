package cn.edu.whu.metro;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * TODO
 *
 * @author thomas
 * @version 1.0
 * @date 2021/3/28 9:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MetroApplication.class)
@WebAppConfiguration
public class MetroApplicationTest {

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
