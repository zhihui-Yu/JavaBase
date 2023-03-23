package com.example;

import java.lang.management.ManagementFactory;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author simple
 */
public class Main {
    public static void main(String[] args) {
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("main: JVM pid is -> " + pid);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(Main::print, 2, 2, TimeUnit.SECONDS);
    }

    private static void print() {
        System.out.println("main: Hello world!" + ZonedDateTime.now());
    }
}