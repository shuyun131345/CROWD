package com.atguigu.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalHandler {

    /**
     * portal首页
     * @return
     */
    @RequestMapping("/")
    public String showPortal(){
        return "portal";
    }
}
