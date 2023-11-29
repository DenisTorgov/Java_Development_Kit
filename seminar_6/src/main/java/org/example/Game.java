package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math3.stat.Frequency;

public class Game {
    private Random rnd = new Random();
    static Frequency frequency = new Frequency();
    public static HashMap<Integer, Integer> firstChoiceResults = new HashMap<>();
    public static HashMap<Integer, Integer> secondChoiceResults = new HashMap<>();
    private final static int TRIES = 1000;
    public Game () {

    }
    public static void Start () {
        System.out.println("Игра начинается");
        Draw draw = new Draw();
        for (int i=0; i < TRIES; i++) {
            firstChoiceResults.put(i, draw.FirstChoice());
            secondChoiceResults.put(i, draw.SecondChoice());
        }
        System.out.println();
        System.out.println("Результаты игры без изменения двери:");
//        System.out.println(firstChoiceResults);
        WinsCount(firstChoiceResults);
        System.out.println();
        System.out.println("Результаты игры если изменить дверь:");
//        System.out.println(secondChoiceResults);
        WinsCount(secondChoiceResults);
    }
    private static void WinsCount(HashMap<Integer, Integer> hm) {
        frequency.clear();
        for (Map.Entry<Integer, Integer> entry: hm.entrySet()){
            frequency.addValue(entry.getValue());
        }
        System.out.println("Количество побед: " + frequency.getCount(1));
        System.out.printf("Вероятность победы: %.1f", (1 - frequency.getCumPct(0)) * 100);
    }
}
