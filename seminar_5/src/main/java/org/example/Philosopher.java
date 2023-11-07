package org.example;

import java.util.concurrent.CountDownLatch;

public class Philosopher extends  Thread {
    University u;
    String name;
    public String action;
    private int meals;
    public static final String THINKS = "thinks";
    public static final String EATING = "eating";

    Philosopher(String name, University u, int meals) {
        super("Philosofer: " + name);
        this.u = u;
        this.name = name;
        this.meals = meals;
        action = THINKS;
        System.out.println("Philosofer " + name + " created");
    }
    public void run() {
        System.out.println(currentThread().getName() + " " + action);
        while (meals > 0) {
            tryToEat(u, this);
            action = THINKS;
            changeForkState(u, this, false);
        }
    }
    private synchronized void tryToEat(University u, Philosopher ph) {
        if(isForksUsed(u, ph)) {
            ph.action = EATING;
            meals--;
            System.out.println(currentThread().getName() + " "
                    + action + ". "+ meals + " meals left.");
        }
//        try {
//            wait();
//        } catch (InterruptedException e) {
//            System.out.println("Thread interruption");
//        }
    }
    private boolean isForksUsed(University u, Philosopher ph) {
        int i = Integer.parseInt(ph.name);
        if (i == 1) {
            if (!u.forks[u.forks.length-1] && !u.forks[0]) {
                changeForkState(u, ph,true);
                notifyAll();
                return true;
            }
            return false;
        }
        if ((!u.forks[i-2] && !u.forks[i-1]) || (i == u.forks.length)) {
            changeForkState(u, ph,true);
            notifyAll();
            return  true;
        }
        return false;
    }
    private void changeForkState(University u, Philosopher ph, boolean b) {
        int i = Integer.parseInt(ph.name);
        if (i == 1) {
            u.forks[u.forks.length-1] = b;
            u.forks[0] = b;
        } else {
            u.forks[i-2] = b;
            u.forks[i-1] = b;
        }
    }
}
