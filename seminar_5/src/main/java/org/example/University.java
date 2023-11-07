package org.example;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class University extends Thread {
    public static final int TABLELENGTH = 5;
    public static ArrayList<Philosopher> philosophers = new ArrayList<>(TABLELENGTH);
//    fork available to use if its value is false
    public boolean[] forks = new boolean[TABLELENGTH];
    private int meals = 3;
    University() {
        super();
        System.out.println("University created");
    }
    public void run() {
        for (int i = 1; i <= TABLELENGTH; i++) {
            philosophers.add(new Philosopher(String.valueOf(i),this, meals));
            philosophers.get(i-1).start();
        }
    }

}
