package com.zh.chrome.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MilitaryUtil {

    // resource util
    @Resource
    GameUtil gameUtil;

    public static final int MAX_WAR_NUMBER = 84;

    public int getWarSumNumber() {
        return MAX_WAR_NUMBER - getNoWarSumNumber();
    }

    /**
     * 获取所有出征兵力
     * @return
     */
    public int getNoWarSumNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {

            gameUtil.changeTab("内政", "军事");
            // return
            return (int)(double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[1]/div[2]/div[1]/div[1]/span[3]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }

    /**
     * 获取未出征兵力
     * @return
     */
    public int getNoWarNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (int)(double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[1]/div[2]/div[1]/div[1]/span[2]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }


    /**
     * 获取城墙耐久
     * @return
     */
    public double getWallNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[3]/span/span", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }


    /**
     * 获取恶魔数量
     * @return
     */
    public int getDemonNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (int)(double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[2]/span[2]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text, "：", 1);
            });
        }
    }


    /**
     * 获取留守数量
     * @return
     */
    public double getWaitNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[3]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }


    /**
     * 获取队伍数量
     * @return
     */
    public int getTeamNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (int)(double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[7]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }


    /**
     * 获取成员数量
     * @return
     */
    public int getMemberNumber() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            // return
            return (int)(double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[11]", (element) -> {
                String text = element.getText();
                return gameUtil.str2double(text);
            });
        }
    }

    // a method name is addWarNumber
    // param double number
    // return void
    // lock

    /**
     * 添加出征兵力
     * @param number
     */
    public void addWarNumber(double number) {
        if (number <= 0) {
            return;
        }
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
//            /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[4]
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[4]", (element) -> {
               gameUtil.ctrlAndClick(element);
            });
        }
        addWarNumber(number - 10);
    }

    // method is subWarNumber
    // param double number
    // return void
    // lock

    /**
     * 减少出征兵力
     * @param number
     */
    public void subWarNumber(double number) {
        if (number <= 0) {
            return;
        }
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[2]", (element) -> {
                gameUtil.ctrlAndClick(element);
            });
        }
        subWarNumber(number - 10);
    }

    // method is add team number
    // param double number
    // return void
    // lock
    public void addTeamNumber(double number) {
        if (number <= 0) {
            return;
        }
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[8]", (element) -> {
                gameUtil._clickAndSleep(element);
            });
        }
        addTeamNumber(number - 1);
    }


    // /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[6]
    // method is sub team number
    // param double number
    // return void
    // lock
    public void subTeamNumber(double number) {
        if (number <= 0) {
            return;
        }
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[6]", (element) -> {
                gameUtil._clickAndSleep(element);
            });
        }
        subTeamNumber(number - 1);
    }

    // method is add member number
    // param double number
    // return void
    // lock
    // /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[12]
    public void addMemberNumber(double number) {
        if (number <= 0) {
            return;
        }

        // if number ge 10 then ctrl and click
        // else click and sleep
        if (number >= 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                gameUtil.changeTab("内政", "军事");
                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[12]", (element) -> {
                    gameUtil.ctrlAndClick(element);
                });
            }
            addMemberNumber(number - 10);
        } else {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                gameUtil.changeTab("内政", "军事");
                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[12]", (element) -> {
                    gameUtil._clickAndSleep(element);
                });
            }
            addMemberNumber(number - 1);
        }

    }

    // /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[10]
    // method is sub member number
    // param double number
    // return void
    // lock
    public void subMemberNumber(double number) {
        if (number <= 0) {
            return;
        }

        // if number ge 10 then ctrl and click
        // else click and sleep
        if (number >= 10) {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                gameUtil.changeTab("内政", "军事");
                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[10]", (element) -> {
                    gameUtil.ctrlAndClick(element);
                });
            }
            subMemberNumber(number - 10);
        } else {
            // lock LOCK_Handle
            synchronized (GameLock.LOCK_Handle) {
                gameUtil.changeTab("内政", "军事");
                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[10]", (element) -> {
                    gameUtil._clickAndSleep(element);
                });
            }
            subMemberNumber(number - 1);
        }
    }


    public double howMuch() {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");
            return (double) gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[13]/button", (element) -> {
                return gameUtil.str2double(element.getAttribute("aria-label"), "：", 1);
            });
        }
    }

    // /html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[2]/div/div[4]/span[13]/button
    // method is recruit
    // param is double number
    // return void
    // if number gt 10 then ctrl and click
    // else if number gt 0 then click and sleep
    // recurrence
    // lock

    /**
     * 军队招募
     * @param number
     */
    public void recruit(double number) {
        if (number <= 0) {
            return;
        }
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {
            gameUtil.changeTab("内政", "军事");

            // if number ge 10 then ctrl and click
            // else click and sleep
            if (number >= 10) {

                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[1]/div[2]/div[2]/button", (element) -> {
                    gameUtil.ctrlAndClick(element);
                });
                recruit(number - 10);
            } else {
                gameUtil.handle("/html/body/div[2]/div/div[2]/div/div/section/div[3]/div/div/section/div[4]/div[1]/div[2]/div[2]/button", (element) -> {
                    gameUtil._clickAndSleep(element);
                });
                recruit(number - 1);
            }

        }
    }

}
