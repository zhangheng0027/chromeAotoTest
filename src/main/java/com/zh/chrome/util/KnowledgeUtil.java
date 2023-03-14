package com.zh.chrome.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KnowledgeUtil {

    // resource gameUtils
    @Resource
    GameUtil gameUtil;

//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[10]/span[1]
//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[45]/span[1]
    // add know1 method
    public void andKnow1() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("文明", "霉菌人星系");

            // handle and ctrl and click
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[10]/span[1]", (element) -> {
                gameUtil.ctrlAndClick(element);
                gameUtil.ctrlAndClick(element);
                return true;
            });

            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[45]/span[1]", (element) -> {
                gameUtil.ctrlAndClick(element);
                gameUtil.ctrlAndClick(element);
                return true;
            });
        }
    }

//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[10]/span[2]
//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[45]/span[2]
    // sub know1 method
    public void subKnow1() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("文明", "霉菌人星系");

            // handle and click
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[10]/span[2]", (element) -> {
                gameUtil.ctrlAndClick(element);
                return true;
            });

            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[2]/div[45]/span[2]", (element) -> {
                gameUtil.ctrlAndClick(element);
                return true;
            });
        }
    }

    // add know2 method
    // change tab to 文明 遥远星系
    // handle and ctrl and click
//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[7]/span[1]
// /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[23]/span[1]
    public void andKnow2() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("文明", "遥远星系");

            // handle and ctrl and click
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[7]/span[1]", (element) -> {
                gameUtil.ctrlAndClick(element);
                return true;
            });

            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[23]/span[1]", (element) -> {
                gameUtil._clickAndSleep(element);
                return true;
            });
        }
    }

    // sub know2 method
    // change tab to 文明 遥远星系
    // handle and click
// /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[7]/span[1]
//    /html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[23]/span[2]
    public void subKnow2() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("文明", "遥远星系");

            // handle and click
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[7]/span[2]", (element) -> {
                gameUtil.ctrlAndClick(element);
                return true;
            });

            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[2]/div/div/section/div[3]/div[23]/span[2]", (element) -> {
                gameUtil._clickAndSleep(element);
                return true;
            });
        }
    }

}
