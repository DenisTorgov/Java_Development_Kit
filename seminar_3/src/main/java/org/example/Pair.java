package org.example;

public class Pair <T, V> {
    private T t;
    private V v;
    public Pair (T t, V v) {
        this.t = t;
        this.v = v;
    }
    public T getFirst() {
        return t;
    }
    public V getSecond() {
        return v;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First arg: " + t + "\n");
        sb.append("Second arg: " + v + "");
        return sb.toString();
    }
}
