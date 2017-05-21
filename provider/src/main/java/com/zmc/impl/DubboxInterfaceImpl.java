package com.zmc.impl;

import com.zmc.DubboxInterface;
import org.springframework.stereotype.Service;

/**
 * Created by zhongmc on 2017/5/21.
 */
@Service
public class DubboxInterfaceImpl implements DubboxInterface {
    @Override
    public String hello(String name) {
        return "hello "+name;
    }
}
