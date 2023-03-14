package com.zh.chrome.controller;

import com.zh.chrome.util.GameUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    GameUtil gameUtil;

    // requestMapping method /test
    @RequestMapping(value = "/test")
    public void test() {
        abc();
    }

    // requestMapping method /abc
    @RequestMapping(value = "/abc")
    public void abc() {
        System.out.println(gameUtil);
    }

    // requestMapping method /changeTab
    @RequestMapping(value = "/changeTab/{path}")
    public void changeTab(@PathVariable String path) {
        gameUtil.changeTab(path);
    }

    @RequestMapping(value = "/changeTab/{path}/{index}")
    public void changeTab(@PathVariable String path, @PathVariable String index) {
        gameUtil.changeTab(path, index);
    }

}
