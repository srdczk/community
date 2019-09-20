package com.czk.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by srdczk 2019/9/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init.sql")
public class SqlTests {
    @Test
    public void contextLoads() {
    }
}
