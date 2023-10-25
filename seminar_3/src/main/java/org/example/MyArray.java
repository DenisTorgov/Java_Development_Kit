package org.example;

import java.io.Serializable;
import java.lang.reflect.Array;

public class MyArray <T> {
    T[] t;
    public MyArray (T[] t) {
        this.t = t;
    }
    public MyArray () {}
    public <T> boolean compareArray (T[] a, T[] b) {
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

}
