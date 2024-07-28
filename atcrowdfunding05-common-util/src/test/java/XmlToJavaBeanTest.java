import com.atguigu.crowd.common.XmlBuilder;
import com.atguigu.crowd.entity.Body;
import com.atguigu.crowd.entity.City;
import com.atguigu.crowd.entity.CityList;
import com.atguigu.crowd.entity.Nation;
import org.junit.Test;

import java.util.List;

public class XmlToJavaBeanTest {
    @Test
    public void testXml(){

        //单元测试相对路径为当前模块
        String filePath = "src/main/resources/city.xml";
        CityList list = (CityList)XmlBuilder.xmlFileToJavaBean(CityList.class, filePath);
        List<City> cityList = list.getCityList();
        cityList.stream().forEach(System.out::println);

    }

    @Test
    public void testXmlFile(){
        //单元测试相对路径为当前模块
        String filePath = "src/main/resources/nation.xml";
        Nation nation = (Nation)XmlBuilder.xmlFileToJavaBean(Nation.class, filePath);
        Body body = nation.getBody();
        List<City> cityList = body.getCityList();
        cityList.stream().forEach(System.out::println);
    }




}
