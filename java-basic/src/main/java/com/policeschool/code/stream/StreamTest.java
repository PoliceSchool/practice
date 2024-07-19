package com.policeschool.code.stream;

import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 1).limit(3).forEach(i -> System.out.printf("%d@main ", i));
        System.out.println();
    }
}
