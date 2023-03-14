package com.zh.chrome.util;

import com.zh.chrome.scheduld.MilitaryScheduled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GameUtilTest {

    // resouce GameUtil
    @Resource
    GameUtil gameUtil;

    // test method testChangeTab
    @Test
    void testChangeTab() {
        gameUtil.changeTab("内政", "行业");

        // 计时开始
        long start = System.currentTimeMillis();
        gameUtil.changeTab("内政", "行业");

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // resource LeftTabUtil
    @Resource
    LeftTabUtil leftTabUtil;

    // a test method getSpeed and count time
    @Test
    void testGetSpeed() {
        // 计时开始
        long start = System.currentTimeMillis();

        // 执行方法
//        System.out.println(leftTabUtil.getSpeed("$"));
//
//        // print getVolume
//        System.out.println(leftTabUtil.getVolume("铝"));
//
//        // print getNumber
//        System.out.println("number: " + leftTabUtil.getNumber("铝"));

        // test buyAll method and attribute value is 胶合板
//        leftTabUtil.buyAll("胶合板");

        // test and print isFull and attribute value is 食物
        System.out.println(leftTabUtil.isFull("食物"));

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // resource Military
    @Resource
    MilitaryUtil military;

    // test method testMilitary
    @Test
    void testMilitary() {
        System.out.println(military.getWarSumNumber());
        // 计时开始
        long start = System.currentTimeMillis();

        // print team number
        System.out.println(military.getTeamNumber());

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // test change 资源 市场
    @Test
    void testChangeMarket() {
        // 计时开始
        long start = System.currentTimeMillis();

        gameUtil.changeTab("资源", "存储");

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // resource populationUtil
    @Resource
    PopulationUtil populationUtil;

    @Test
    void testGetPopulationNumber() {
        // 计时开始
        long start = System.currentTimeMillis();

        System.out.println(populationUtil.getPopulation("矿工"));

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // resource MilitaryScheduled
    @Resource
    MilitaryScheduled militaryScheduled;

    // test method military
    @Test
    void testMilitaryScheduled() {
        // 计时开始
        long start = System.currentTimeMillis();

        militaryScheduled.military();

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // test double 2 int
    @Test
    void testDouble2Int() {
        // 计时开始
        long start = System.currentTimeMillis();

        System.out.println((int) 1.0);

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // main
    public static void main(String[] args) {
        // 计时开始
        long start = System.currentTimeMillis();

        Double a = 1.0;
        System.out.println((int)(double) a);

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

    // test howMuch
    @Test
    void testHowMuch() {
        // 计时开始
        long start = System.currentTimeMillis();

        System.out.println(military.howMuch());

        // 计时结束
        long end = System.currentTimeMillis();

        // 输出时长
        System.out.println("耗时：" + (end - start) + "ms");
    }

}