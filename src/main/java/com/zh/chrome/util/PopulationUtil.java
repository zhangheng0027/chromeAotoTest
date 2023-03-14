package com.zh.chrome.util;

import org.junit.jupiter.api.Order;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PopulationUtil {

    // resource util
    @Resource
    GameUtil gameUtil;

    // define Map name is populationMap
    private static final Map<String, String> populationMap = new ConcurrentHashMap<>();

    // method is _init
    // springboot start event
    @Order(201)
    @EventListener(ApplicationReadyEvent.class)
    public void _init() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {

            // change tab to 内政 政府
            gameUtil.changeTab("内政", "政府");

            // find elements /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[1]/div/div[1]/div[2]/div[1]
            // and put to populationMap
            gameUtil.scanALabel("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[1]/div/div[1]/div[2]/div", populationMap);

        }
    }

    /**
     * 获取人口
     * @param name
     * @return
     */
    public int getPopulation(String name) {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            // change tab to 内政 政府
            gameUtil.changeTab("内政", "政府");
            // return

            return (int)(double) gameUtil.handle(populationMap.get(name) + "/div[1]/span", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text, "/", 0);
            });
        }
    }

    /**
     * 获取人口上限
     * @param name
     * @return
     */
    public double getPopulationLimit(String name) {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            // change tab to 内政 政府
            gameUtil.changeTab("内政", "政府");
            // return
            return (double) gameUtil.handle(populationMap.get(name) + "/div[1]/span", (element) -> {
                String text = element.getText();

                // if text not contains "/"
                // return int max
                if (!text.contains("/")) {
                    return Integer.MAX_VALUE;
                }

                return gameUtil.str2double(text, "/", 1);
            });
        }
    }



    /**
     * 增加人口
     * @param name
     * @param number
     */
//    /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[1]/div/div[1]/div[2]/div[5]/div[2]/span[2]
    // if number le 0 return before lock
    // if number gt 10 then ctrl and click
    // else click
    public void addPopulation(String name, int number) {

        // if number le 0 return before lock
        if (number <= 0) {
            return;
        }

        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            // change tab to 内政 政府
            gameUtil.changeTab("内政", "政府");

            // if number gt 10 then handle element and  ctrlAndClick
            // else handle element and click
            if (number > 10) {
                gameUtil.handle(populationMap.get(name) + "/div[2]/span[2]", (element) -> {
                    gameUtil.ctrlAndClick(element);
                    return null;
                });

                // recurrence
                addPopulation(name, number - 10);

            } else {
                gameUtil.handle(populationMap.get(name) + "/div[2]/span[2]", (element) -> {
                    gameUtil._clickAndSleep(element);
                    return null;
                });

                // recurrence
                addPopulation(name, number - 1);
            }
        }
    }


    /**
     * 减少人口
     * @param name
     * @param number
     */
//    /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[1]/div/div[1]/div[2]/div[5]/div[2]/span[1]
    public void subPopulation(String name, int number) {

        // if number le 0 return before lock
        if (number <= 0) {
            return;
        }

        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            // change tab to 内政 政府
            gameUtil.changeTab("内政", "政府");

            // if number gt 10 then handle element and  ctrlAndClick
            // else handle element and click
            if (number > 10) {
                gameUtil.handle(populationMap.get(name) + "/div[2]/span[1]", (element) -> {
                    gameUtil.ctrlAndClick(element);
                    return null;
                });

                // recurrence
                subPopulation(name, number - 10);

            } else {
                gameUtil.handle(populationMap.get(name) + "/div[2]/span[1]", (element) -> {
                    gameUtil._clickAndSleep(element);
                    return null;
                });

                // recurrence
                subPopulation(name, number - 1);
            }
        }
    }


}
