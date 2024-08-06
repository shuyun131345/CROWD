import com.atguigu.crowd.thread.FileRunnable;
import com.atguigu.crowd.thread.ThreadPoolUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ThreadTest {
    public static void main(String[] args) {

        //模拟10个请求
        for (int i =1; i <= 10; i++){
            ThreadPoolUtil.execute(new FileRunnable());
        }



    }
}
