package org.example;

import java.util.Random;

public class Draw {
    private Random rnd = new Random();
    boolean flagOuter, flagInner;

    private int Choose() {
        return rnd.nextInt(1, 4);
    }

    public int FirstChoice() {
        int prize = Choose();
        int choice = Choose();
        if (prize == choice) {
            return 1;
        }
        return 0;
    }

    public int SecondChoice() {
        int prize = Choose();
        int choice = Choose();
        int outChoise, secondChoice;
        flagOuter = true;
        flagInner = true;
        while (flagOuter) {
            outChoise = Choose();
            if (outChoise != prize && outChoise != choice) {
                while (flagInner) {
                    secondChoice = Choose();
                    if (outChoise != secondChoice && secondChoice != choice) {
                        flagInner = false;
                        choice = secondChoice;
                    }
                }
                flagOuter = false;
            }
        }
        if (prize == choice) {
            return 1;
        }
        return 0;
    }
}
