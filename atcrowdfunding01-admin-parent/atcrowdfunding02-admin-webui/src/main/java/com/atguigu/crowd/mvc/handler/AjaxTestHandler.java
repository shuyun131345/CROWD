package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxTestHandler {

    private static final Logger log = LoggerFactory.getLogger(AjaxTestHandler.class);

    @RequestMapping("/send/object/student.html")
    @ResponseBody
    public String ajaxTest(@RequestBody Student list){
        log.info(list.toString());
//        list.stream().forEach(System.out::println);

        //空指针异常
        String str = null;
        System.out.println(str.length());

        return "target";
    }
}
