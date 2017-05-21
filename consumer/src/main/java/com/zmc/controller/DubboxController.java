package com.zmc.controller;

import com.zmc.DubboxInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhongmc on 2017/5/21.
 */
@Controller
public class DubboxController {
    @Autowired
    private DubboxInterface dubboxInterface;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return dubboxInterface.hello("dubbox");
    }
}
