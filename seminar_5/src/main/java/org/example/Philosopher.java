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
        }
    }
    private synchronized void tryToEat(University u, Philosopher ph) {
        if(isForksUsed(u, ph)) {
            ph.action = EATING;
            meals--;
            System.out.println(currentThread().getName() + " "
                    + action + ". "+ meals + " meals left.");
        }
        changeForkState(u, ph);
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
                u.forks[u.forks.length-1] = true;
                u.forks[0] = true;
                notifyAll();
                return true;
            }
            return false;
        }
        if ((!u.forks[i-2] && !u.forks[i-1]) || (i == u.forks.length)) {
            changeForkState(u, ph);
            notifyAll();
            return  true;
        }
        return false;
    }
    private void changeForkState(University u, Philosopher ph) {
        int i = Integer.parseInt(ph.name);
        if (i == 1) {
            u.forks[u.forks.length-1] = !u.forks[u.forks.length-1];
            u.forks[0] = !u.forks[0];
        } else {
            u.forks[i-2] = !u.forks[i-2];
            u.forks[i-1] = !u.forks[i-1];
        }
    }
}
