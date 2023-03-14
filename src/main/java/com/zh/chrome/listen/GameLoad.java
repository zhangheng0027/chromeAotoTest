package com.zh.chrome.listen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GameLoad {

    // resource ChromeDriver
    @Resource
    ChromeDriver driver;

    public void test() {
        WebElement element = driver.findElement(By.xpath(""));
//        element
    }

}
