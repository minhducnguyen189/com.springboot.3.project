package com.springboot.project.helper;

import java.util.Random;

public class RandomHelper {

    public static void randomSleepInSeconds() {
        try {
            int randomMilliSeconds = (new Random().nextInt(9) + 1) * 1000;
            System.out.println("Random In Seconds: " + randomMilliSeconds);
            Thread.sleep(randomMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
