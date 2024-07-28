import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.inf.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;


    @Test
    public void testAdminService(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Admin admin = new Admin(null, "tony", "123456", "tony", "12345@qq.com", sdf.format(new Date()));
        Integer integer = adminService.insertAdmin(admin);
        System.out.println("数据库受影响条数为："+integer);

    }



    @Test
    public void selectTest(){
        Admin admin = adminMapper.selectByPrimaryKey(10000);
        System.out.println(admin.toString());
    }



    @Test
    public void testInsertAdmin(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int insert = adminMapper.insert(new Admin(10000,"tom","123456","tom","123@qq.com",sdf.format(new Date())));
        System.out.println("受影响条数为："+insert);

    }

    @Test
    public void testDataSourceConnection() throws SQLException {

        Connection connection = dataSource.getConnection();

        System.out.println(connection);

    }
}
