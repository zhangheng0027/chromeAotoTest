package com.zh.chrome.util;


import org.junit.jupiter.api.Order;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LeftTabUtil {

    @Resource
    GameUtil gameUtil;

    // resource ChromeDriver
    @Resource
    ChromeDriver driver;

    // define Map name is sourceMap
    private static final Map<String, String> sourceMap = new ConcurrentHashMap<>();

    @Order(1)
    @EventListener(ApplicationReadyEvent.class)
    public void _init() {
        // lock
        synchronized (GameLock.LOCK_Handle) {
            String path = "/html/body/div[2]/div/div[1]/div[3]/div";
            gameUtil.scanALabel(path, sourceMap);
        }
    }

    /**
     * 获取容量
     * @param name
     * @return
     */
    public double getVolume(String name) {
        return (double) gameUtil.handle(sourceMap.get(name) + "/span[1]", (element) -> {
            String text = element.getText();
            return gameUtil.str2double(text, "/", 1);
        });
    }

    /**
     * 获取当前数量
     * @param name
     * @return
     */
    public double getNumber(String name) {
        return (double) gameUtil.handle(sourceMap.get(name) + "/span[1]", (element) -> {
            String text = element.getText();
            return gameUtil.str2double(text, "/", 0);
        });
    }

    /**
     * 获取生产速度
     * @param name
     * @return
     */
    public double getSpeed(String name) {
        return (double) gameUtil.handle(sourceMap.get(name) + "/span[3]", (element) -> {
            String text = element.getText();
            return gameUtil.str2double(text, "/", 0);
        });
    }


    public void buyOne(String name) {
        gameUtil.handle(sourceMap.get(name) + "/span[2]/span[1]/a", (element) -> {
            gameUtil._clickAndSleep(element, 330);
        });
    }

    // buy 5
    public void buyFive(String name) {
        gameUtil.handle(sourceMap.get(name) + "/span[2]/span[2]/a", (element) -> {
            gameUtil._clickAndSleep(element, 330);
        });
    }

    // buy all
    public void buyAll(String name) {
        gameUtil.handle(sourceMap.get(name) + "/span[2]/span[3]/a", (element) -> {
            gameUtil._clickAndSleep(element, 330);
        });
    }

    // method name is isFull
    public boolean isFull(String name) {
        return (boolean) gameUtil.handle(sourceMap.get(name) + "/span[1]", (element) -> {
            return element.getAttribute("class").contains("warning");
        });
    }

}
