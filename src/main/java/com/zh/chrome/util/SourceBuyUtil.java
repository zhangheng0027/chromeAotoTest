package com.zh.chrome.util;

import org.junit.jupiter.api.Order;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SourceBuyUtil {

    // resource GameUtil name is util
    @Resource
    GameUtil util;

    // resource driver
    @Resource
    ChromeDriver driver;

    // define a Map
    // name is sourceMaps
    // key is String
    // value is String
    private static final Map<String, String> sourceMaps = new ConcurrentHashMap<>();

    // a method name is _init
//     spring boot start after run this method
    @Order(401)
    @EventListener(ApplicationReadyEvent.class)
    public void _init() {
        synchronized (GameLock.LOCK_Handle) {
            util.changeTab("资源", "市场");
            String path = "/html/body/div[2]/div/div[2]/div/div/section/div[5]/div/div/section/div[1]/div";
            util.scanALabel(path, sourceMaps);
        }
    }


    // define AtomicInteger name is lastNumber
     private static AtomicInteger lastNumber = new AtomicInteger(0);

    // a method name is inputNumber
    // parameter number
    // this number compare lastNumber
    // if abs number - lastNumber < 1000 then return
    public void inputNumber(int number) {
        int last = lastNumber.get();
        if (Math.abs(number - last) < 1000) {
            return;
        }
        synchronized (GameLock.LOCK_Handle) {
            util.changeTab("资源", "市场");
            String path = "/html/body/div[2]/div/div[2]/div/div/section/div[5]/div/div/section/div[1]/div[1]/div/div/div/div/div/input";
            boolean f = (boolean) util.handle(path, (element) -> {
                element.clear();
                element.sendKeys(String.valueOf(number));
                util.sleep(330);
            });
            if (f)
                lastNumber.set(number);
        }
    }


    // method of one param and method name is buy
    // param name
    public void buy(String name) {
        buy(name, lastNumber.get());
    }


    /**
     * 购买
     * @param name
     * @param number
     */
    public void buy(String name, int number) {
        // sourceMaps if not containKey name then return
        if (!sourceMaps.containsKey(name)) {
            return;
        }
        // input number
        // lock
        synchronized (GameLock.LOCK_Handle) {
            inputNumber(number);
            util.handle(sourceMaps.get(name) + "/span[2]", (element) -> {
                util._clickAndSleep(element, 330);
            });
        }
    }

    // a method name is sell
    // parameter name
    // parameter number
    /**
     * 出售
     * @param name
     * @param number
     */
    public void sell(String name, double number) {
        // sourceMaps if not containKey name then return
        if (!sourceMaps.containsKey(name)) {
            return;
        }
        // input number
        // lock
        synchronized (GameLock.LOCK_Handle) {
            inputNumber((int) number);
            util.handle(sourceMaps.get(name) + "/span[4]", (element) -> {
                util._clickAndSleep(element, 330);
            });
        }
    }


}
