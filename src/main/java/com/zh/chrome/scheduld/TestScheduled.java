package com.zh.chrome.scheduld;

import com.zh.chrome.util.GameLock;
import com.zh.chrome.util.GameUtil;
import com.zh.chrome.util.LeftTabUtil;
import com.zh.chrome.util.SourceBuyUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
@Component
public class TestScheduled {

    @Resource
    GameUtil gameUtil;

    // resource LeftTabUtil
    @Resource
    LeftTabUtil leftTabUtil;

    // resource SourceBuyUtil
    @Resource
    SourceBuyUtil sourceBuyUtil;

    // define Atomic boolean name is stop
    public static final AtomicBoolean stop = new AtomicBoolean(false);

    @Scheduled(fixedDelay = 60, initialDelay = 40, timeUnit = TimeUnit.SECONDS)
    public void buySimple() {

        if (stop.get()) {
            return;
        }

        // if 木材 is full and speed gt 10 then buy all 胶合板
        if (leftTabUtil.isFull("木材") && leftTabUtil.getSpeed("木材") > 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                leftTabUtil.buyAll("胶合板");
            }
        }

        // if 水泥 is full and speed gt 10 then buy all 砌砖
        if (leftTabUtil.isFull("水泥") && leftTabUtil.getSpeed("水泥") > 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                leftTabUtil.buyAll("砌砖");
            }
        }

        // if 铁 is full and speed gt 10 then buy all 锻铁
        if (leftTabUtil.isFull("铁") && leftTabUtil.getSpeed("铁") > 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                leftTabUtil.buyAll("锻铁");
            }
        }

        // if 铝 is full and speed gt 10 then buy all 金属板
        if (leftTabUtil.isFull("铝") && leftTabUtil.getSpeed("铝") > 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                leftTabUtil.buyAll("金属板");
            }
        }

        // use lock LOCK_Handle to lock
        synchronized (GameLock.LOCK_Handle) {
            choseBuy("$");
            choseBuy("石头");
            choseBuy("食物");
            choseBuy("毛皮");
            choseBuy("铜");
            choseBuy("钢");
        }
    }



    // a method name is choseBuy
    public void choseBuy(String name) {

        double speed = leftTabUtil.getSpeed(name);
        if (!leftTabUtil.isFull(name) || speed < 10) {
            return;
        }
        sourceBuyUtil.sell(name, speed * 10 * 60);

        // get 合金 number and get 铱 number
        double alloy = leftTabUtil.getNumber("合金");
        double iridium = leftTabUtil.getNumber("铱");

        // if alloy * 2.5 gt iridium then buy 铱
        // else by 合金
        if (alloy * 2.5 > iridium) {
            // use log print info
            log.info("buy iridium");
            sourceBuyUtil.buy("铱");
        } else {
            // use log print info
            log.info("buy alloy");
            sourceBuyUtil.buy("合金");
        }
    }



}
