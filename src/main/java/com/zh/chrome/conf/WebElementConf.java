package com.zh.chrome.conf;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WebElementConf {


    private static final String URL = "https://pmotschmann.github.io/";


    static {
        System.setProperty("webdriver.chrome.driver", "E:\\driver\\110.0.5481.77\\chromedriver.exe");
    }

    @Bean
    public ChromeOptions options() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        return options;
    }


    @Bean
    public ChromeDriver driver(ChromeOptions options) {
        ChromeDriver driver = new ChromeDriverC(options);
        if (!driver.getCurrentUrl().startsWith(URL)) {
            Set<String> windowHandles = driver.getWindowHandles();
            String handleId = "CDwindow-2FC402C191FC1EE616C97CFC9AAA0F8F";
            if (windowHandles.contains(handleId)) {
                driver.switchTo().window(handleId);
            } else {
                for (String handle : windowHandles) {
                    String url = driver.switchTo().window(handle).getCurrentUrl();
                    if (url.startsWith(URL)) {
                        System.out.println(handle);
                        break;
                    }
                }
            }
        }
        return driver;
    }


    class ChromeDriverC extends ChromeDriver {

        public ChromeDriverC(ChromeOptions o) {
            super(o);
        }

        @Override
        public void close() {
        }

        public void _close() {
            super.close();
        }
    }

}
