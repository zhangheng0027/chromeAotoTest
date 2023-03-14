package com.zh.chrome.util;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class GameUtil {

    @Resource
    ChromeDriver driver;


    private static final Map<String, WebElement> elementMap = new ConcurrentHashMap<>();

    private static final Map<String, String> xpathShine = new ConcurrentHashMap<>();

    // a handle method attribute is xpath and consumer
    public boolean handle(String xpath, Consumer<WebElement> consumer) {
        return (boolean) handle(xpath, (element) -> {
            consumer.accept(element);
            return true;
        });
    }

    // use xpath to find element and use consumer to handle element
    public Object handle(String xpath, Function<WebElement, Object> function) {

        // use xpath to find element
        try {
            WebElement element = elementMap.get(xpath);
            if (element == null) {
                element = driver.findElement(By.xpath(xpath));
                elementMap.put(xpath, element);
            }

            // is element null return false
            if (element == null) {
                return false;
            }

            return function.apply(element);
        } catch (Exception e) {
            // if xpath is not empty then remove
            if (StringUtils.isNotEmpty(xpath)) {
                elementMap.remove(xpath);
            }
//            elementMap.remove(xpath);
            return false;
        }

    }

    // method is _clickAndSleep
    // only element
    // sleep is 330
    public void _clickAndSleep(WebElement element) {
        _clickAndSleep(element, 330);
    }

    public void _clickAndSleep(WebElement element, long sleep) {
        element.click();
        sleep(sleep);
    }

    @SneakyThrows
    public void sleep(long sleep) {
        Thread.sleep(sleep);
    }


    private static final Map<String, String> tabMap = new ConcurrentHashMap<>();

    public void changeTab(String tabName) {
        changeTab(tabName, null);
    }

    public void changeTab(String tabName, String name) {
        // use StringUtils judge tabName is null or empty return
        if (StringUtils.isEmpty(tabName)) {
            return;
        }

        if (!tabMap.containsKey(tabName)) {
            _initTabMap1();
        }

        boolean f = (boolean) handle(tabMap.get(tabName), (element) -> {
            String aClass = element.getAttribute("class");
            if (aClass.contains("active")) {
                return;
            }
            // lock
            synchronized (GameLock.LOCK_Handle) {
                _clickAndSleep(element, 500);
            }
//            _clickAndSleep(element, 500);
            return;
        });

        if (!f || StringUtils.isEmpty(name)) {
            return;
        }

        if (!tabMap.containsKey(tabName + "-" + name)) {
            _initTabMap2(tabName);
        }

        handle(tabMap.get(tabName + "-" + name), (element) -> {
            String aClass = element.getAttribute("class");
            if (aClass.contains("active")) {
                return;
            }
            // lock
            synchronized (GameLock.LOCK_Handle) {
                _clickAndSleep(element, 330);
            }
//            _clickAndSleep(element, 330);

        });

    }

    public void _initTabMap1() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[2]/div/div[2]/div/div/nav/ul/li"));
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            String name = element.findElement(By.xpath("a")).getText();
            tabMap.put(name, "/html/body/div[2]/div/div[2]/div/div/nav/ul/li[" + (i + 1) + "]");
        }
    }

    public void _initTabMap2(String tabName) {
        String path = "/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/nav/ul/li";

        if ("资源".equals(tabName)) {
            path = "/html/body/div[2]/div/div[2]/div/div/section/div[5]/div/div/nav/ul/li";
        }

        List<WebElement> elements = driver.findElements(By.xpath(path));
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            // if tableName is 资源 find a
            // else find a/h2
            String name1 = "资源".equals(tabName) ? element.findElement(By.xpath("a")).getText() : element.findElement(By.xpath("a/h2")).getText();

            tabMap.put(tabName + "-" + name1, path + "[" + (i + 1) + "]");
        }

    }

    public double str2double(String str, String split, int index) {
        // use StringUtils judge str is null or empty return 0
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        // str split by split and return str2double
        return str2double(str.split(split)[index]);
    }

    // a attribute str2double
    public double str2double(String str) {
        // use StringUtils judge str is null or empty return 0
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        // str trim
        str = str.trim();

        // if str start $ substring 1
        if (str.startsWith("$")) {
            str = str.substring(1);
        }

        // if str end % substring 0 - 1
        if (str.endsWith("%")) {
            str = str.substring(0, str.length() - 1);
        }

        double bs = 1;

        // str repaceall , to ""
        str = str.replaceAll(",", "");

        // if str end K substring 0 - 1
        if (str.endsWith("K")) {
            str = str.substring(0, str.length() - 1);
            bs = 1000;
        } else if (str.endsWith("M")) {
            str = str.substring(0, str.length() - 1);
            bs = 1000000;
        } else if (str.endsWith("B")) {
            str = str.substring(0, str.length() - 1);
            bs = 1000000000;
        }

        // return str to double
        return Double.parseDouble(str) * bs;
    }

    public void scanALabel(String path, Map<String, String> sourceMaps) {
        List<WebElement> elements = driver.findElements(By.xpath(path));
        String xpath = "/h3";

        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            if (element.isDisplayed()) {
                final int index = i;

                // handle xpath and return boolean
                // if return false then xpath = /span[1]
                // if return false then xpath = /div[1]/h3/a
                boolean f = (boolean) handle(path + "[" + (i + 1) + "]" + xpath, (element1) -> {
                    String text = element1.getText();
                    sourceMaps.put(text, path + "[" + (index + 1) + "]");
                    return true;
                });
                if (!f) {
                    xpath = "/span[1]";
                    f = (boolean) handle(path + "[" + (i + 1) + "]" + xpath, (element1) -> {
                        String text = element1.getText();
                        sourceMaps.put(text, path + "[" + (index + 1) + "]");
                        return true;
                    });
                }
                // if f is false then /div[1]/h3/a
                if (!f) {
                    xpath = "/div[1]/h3/a";
                    f = (boolean) handle(path + "[" + (i + 1) + "]" + xpath, (element1) -> {
                        String text = element1.getText();
                        sourceMaps.put(text, path + "[" + (index + 1) + "]");
                        return true;
                    });
                }

                if (!f) {
                    xpath = "/div[1]/h3";
                    f = (boolean) handle(path + "[" + (i + 1) + "]" + xpath, (element1) -> {
                        String text = element1.getText();
                        sourceMaps.put(text, path + "[" + (index + 1) + "]");
                        return true;
                    });
                }


//                boolean f = (boolean) handle(path + "[" + (i + 1) + "]/h3", (element1) -> {
//                    String text = element1.getText();
//                    sourceMaps.put(text, path + "[" + (index + 1) + "]");
//                    return true;
//                });
//                if (!f) {
//                    f = (boolean) handle(path + "[" + (i + 1) + "]/span[1]", (element1) -> {
//                        String text = element1.getText();
//                        sourceMaps.put(text, path + "[" + (index + 1) + "]");
//                        return true;
//                    });
//                }
//                // if f is false then /div[1]/h3/a
//                if (!f) {
//                    f = (boolean) handle(path + "[" + (i + 1) + "]/div[1]/h3/a", (element1) -> {
//                        String text = element1.getText();
//                        sourceMaps.put(text, path + "[" + (index + 1) + "]");
//                        return true;
//                    });
//                }
            }
        }
    }

    // method name is ctrl and click
    // param is element
    public void ctrlAndClick(WebElement element) {
        // use Actions to ctrl and click element
        new Actions(driver).keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();
        sleep(330);
    }

}
