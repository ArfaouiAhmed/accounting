package com.accounting.backend.common;

import java.util.function.Consumer;

public class Utils {

    public static <T> void setIfGiven(T value, Consumer<T> s) {
        if (value != null) {
            s.accept(value);
        }
    }
}
