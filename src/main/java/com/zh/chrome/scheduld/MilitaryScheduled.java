package com.zh.chrome.scheduld;

import com.zh.chrome.util.GameLock;
import com.zh.chrome.util.MilitaryUtil;
import com.zh.chrome.util.PopulationUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
@Component
public class MilitaryScheduled {

    // resource militaryUtil
    @Resource
    MilitaryUtil militaryUtil;

    // resource populationUtil
    @Resource
    PopulationUtil populationUtil;

    // define Atomic boolean name is occupied
    private static final AtomicBoolean occupied = new AtomicBoolean(false);

    @Scheduled(fixedDelay = 120, initialDelay = 50, timeUnit = TimeUnit.SECONDS)
    public void military() {

        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {

            // getWarSumNumber
            int warSumNumber = militaryUtil.getWarSumNumber();
            // if warSumNumber eq 0 then occupied set true
            if (warSumNumber == 0 && !occupied.get()) {
                occupied.set(true);
            }

            // getNoWarNumber
            int noWarNumber = militaryUtil.getNoWarNumber();
            int noWarSumNumber = MilitaryUtil.MAX_WAR_NUMBER - warSumNumber;

            // if nowarnumber lt noWarSumNumber
            if (noWarNumber < noWarSumNumber) {
                buyWar(noWarSumNumber - noWarNumber);
            }

            noWarNumber = militaryUtil.getNoWarNumber();
            // if not occupied and nowarnumber gt 0
            // then add war number
            if (!occupied.get() && noWarNumber > 0) {
                militaryUtil.addWarNumber(noWarNumber);
                return;
            }

            if (noWarNumber < noWarSumNumber * 0.8) {
                return;
            }

            // addwarNumber
            militaryUtil.addWarNumber(noWarNumber);

            // getDemonNumber
            int demonNumber = militaryUtil.getDemonNumber();
            // get member number
            int memberNumber = militaryUtil.getMemberNumber();

            int teamNumber = militaryUtil.getTeamNumber();

            // if demonmuber gt 5000 then add member to 31
            // else if dominumber gt 2500 then add member to 21 and add team to 2
            // else member to 11 and team to 4
            if (demonNumber > 5000) {
                if (memberNumber > 31) {
                    militaryUtil.subMemberNumber(memberNumber - 31);
                } else if (memberNumber < 31) {
                    militaryUtil.addMemberNumber(31 - memberNumber);
                }

                if (teamNumber > 1) {
                    militaryUtil.subTeamNumber(teamNumber - 1);
                } else if (teamNumber < 1) {
                    militaryUtil.addTeamNumber(1 - teamNumber);
                }
            } else if (demonNumber > 2500) {

                if (memberNumber > 21) {
                    militaryUtil.subMemberNumber(memberNumber - 21);
                } else if (memberNumber < 21) {
                    militaryUtil.addMemberNumber(21 - memberNumber);
                }

                if (teamNumber > 2) {
                    militaryUtil.subTeamNumber(teamNumber - 2);
                } else if (teamNumber < 2) {
                    militaryUtil.addTeamNumber(2 - teamNumber);
                }
            } else if (demonNumber > 2000) {

                if (memberNumber > 11) {
                    militaryUtil.subMemberNumber(memberNumber - 11);
                } else if (memberNumber < 11) {
                    militaryUtil.addMemberNumber(11 - memberNumber);
                }

                if (teamNumber > 4) {
                    militaryUtil.subTeamNumber(teamNumber - 4);
                } else if (teamNumber < 4) {
                    militaryUtil.addTeamNumber(4 - teamNumber);
                }

                // set true
//                occupied.set(true);
            } else {
                // getPopulation is 勘探者
                int population = populationUtil.getPopulation("勘探者");
                // change population to 10
                // if population gt 10 then sub population to 10
                // if population lt 10 then add population to 10
                if (population > 10) {
                    populationUtil.subPopulation("勘探者", population - 10);
                } else if (population < 10) {
                    populationUtil.addPopulation("勘探者", 10 - population);
                }


                // set true
                occupied.set(true);
            }

        }

    }


    // buyWar
    // param number
    private void buyWar(int number) {
        // lock LOCK_Handle
        synchronized (GameLock.LOCK_Handle) {

            // get how much
            double howMuch = militaryUtil.howMuch();

            // if howuch lt 5000 then recruit 10
            // else if howmuch lt 30000 then recruit 1
            // recurrence number -
            if (howMuch < 5000) {
                militaryUtil.recruit(10);
                buyWar(number - 10);
            } else if (howMuch < 30000) {
                militaryUtil.recruit(1);
                buyWar(number - 1);
            }

        }
    }


}
