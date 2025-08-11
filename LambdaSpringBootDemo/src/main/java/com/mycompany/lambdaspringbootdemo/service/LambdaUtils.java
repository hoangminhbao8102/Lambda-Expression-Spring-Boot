/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lambdaspringbootdemo.service;

import java.util.function.Function;

/**
 *
 * @author 20113
 */
public class LambdaUtils {

    @FunctionalInterface
    public interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    public static <T, R> Function<T, R> rethrow(ThrowingFunction<T, R, Exception> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // Composition ví dụ
    public static <T> Function<T, T> compose(Function<T, T> f1, Function<T, T> f2) {
        return f1.andThen(f2);
    }
}
