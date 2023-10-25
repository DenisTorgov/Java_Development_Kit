package org.example;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator<>();
        System.out.println("Hello world!");
        System.out.println(calc.Sum(3, 7));
        System.out.println(calc.Sub(3f, 7));
        System.out.println(calc.Mult(3, 7.0));
        System.out.println(calc.Div(3l, 7f));

        MyArray myArray = new MyArray<>();
        System.out.println(myArray.compareArray(new Object[] {1, 2, 3}, new Object[] {1, 2, 3}));
        System.out.println(myArray.compareArray(new Object[] {1, 2, 3}, new Object[] {1, 2, 4}));
        System.out.println(myArray.compareArray(new Object[] {1.2, 2.5, 3.9}, new Object[] {1.2, 2.5, 3.9}));
        System.out.println(myArray.compareArray(new Object[] {1.2, 2.5, 3.9}, new Object[] {1.2, 2.5, 3.1}));
        System.out.println(myArray.compareArray(new Object[] {"qwe", "asd", "zxc"}, new Object[] {"qwe", "asd", "zxc"}));
        System.out.println(myArray.compareArray(new Object[] {"qwe", "ASD", "zxc"}, new Object[] {"qwe", "asd", "zxc"}));

        Pair pair = new Pair(1, "qwe");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);

    }
}