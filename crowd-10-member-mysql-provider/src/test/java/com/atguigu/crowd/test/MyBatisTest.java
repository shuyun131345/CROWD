package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.po.MemberPo;
import com.atguigu.crowd.mapper.MemberPoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author shuyun
 * @date 2024-10-10 09:56:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {
    Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPoMapper mapper;

    @Test
    public void testConnetion() throws SQLException {
        Connection connection = dataSource.getConnection();
        logger.info("============"+connection.toString());
    }

    @Test
    public void testDatabase(){
        MemberPo memberPo = mapper.selectByPrimaryKey(1001);
        logger.info("======="+memberPo.toString());
    }

}

