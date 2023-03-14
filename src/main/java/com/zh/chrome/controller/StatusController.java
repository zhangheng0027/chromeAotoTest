package com.zh.chrome.controller;

import com.zh.chrome.scheduld.MilitaryScheduled;
import com.zh.chrome.scheduld.TestScheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    // request mapping /get/{name}
    @RequestMapping(value = "/get/{name}")
    public boolean get(@PathVariable String name) {

        if ("military".equals(name)) {
            return MilitaryScheduled.stop.get();
        } else if ("source".equals(name)) {
            return TestScheduled.stop.get();
        }
        return false;
    }

    // request mapping /start/{name}
    @RequestMapping(value = "/start/{name}")
    public void start(@PathVariable String name) {

        if ("military".equals(name)) {
            MilitaryScheduled.stop.set(false);
        } else if ("source".equals(name)) {
            TestScheduled.stop.set(false);
        }
    }

    // request mapping /startAll
    @RequestMapping(value = "/startAll")
    public void startAll() {
        MilitaryScheduled.stop.set(false);
        TestScheduled.stop.set(false);
    }

    // request mapping /stopAll
    @RequestMapping(value = "/stopAll")
    public void stopAll() {
        MilitaryScheduled.stop.set(true);
        TestScheduled.stop.set(true);
    }


    // request mapping /{name}
    @RequestMapping(value = "/stop/{name}")
    public void stop(@PathVariable String name) {

        if ("military".equals(name)) {
            MilitaryScheduled.stop.set(true);
        } else if ("source".equals(name)) {
            TestScheduled.stop.set(true);
        }
    }

}
