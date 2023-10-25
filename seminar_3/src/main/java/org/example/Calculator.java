package org.example;

public class Calculator <T extends Number> {
    T t;
    Calculator (T t) {
        this.t = t;
    }
    Calculator () {
    }
    public T Sum (T a, T b) {
        return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
    }
    public T Sub (T a, T b) {
        return (T) Double.valueOf(a.doubleValue() - b.doubleValue());
    }
    public T Mult (T a, T b) {
        return (T) Double.valueOf(a.doubleValue() * b.doubleValue());
    }
    public T Div (T a, T b) {
        return (T) Double.valueOf(a.doubleValue() / b.doubleValue());
    }
}
