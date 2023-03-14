package com.zh.chrome.controller;

import com.zh.chrome.util.GameLock;
import com.zh.chrome.util.KnowledgeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/know")
public class KnowController {

    // resource knowledgeUtil
    @Resource
    KnowledgeUtil knowledgeUtil;


    // request mapping /startAll
    @RequestMapping(value = "/startAll")
    public void startAll() {
        synchronized (GameLock.LOCK_Handle) {
            knowledgeUtil.andKnow1();
            knowledgeUtil.andKnow2();
        }
    }

    // request mapping /stopAll
    @RequestMapping(value = "/stopAll")
    public void stopAll() {
        synchronized (GameLock.LOCK_Handle) {
            knowledgeUtil.subKnow1();
            knowledgeUtil.subKnow2();
        }
    }


}
