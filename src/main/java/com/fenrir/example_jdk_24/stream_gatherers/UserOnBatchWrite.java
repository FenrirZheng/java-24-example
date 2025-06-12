package com.fenrir.example_jdk_24.stream_gatherers;

import java.util.stream.Gatherers;
import java.util.stream.Stream;


public class UserOnBatchWrite {

    public static void main(String[] args) {
// 使用範例
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.windowFixed(3))
                .toList().forEach(System.out::println);
//> Task :com.fenrir.example_jdk_24.stream_gatherers.Batch.main()
//[1, 2, 3]
//[4, 5, 6]
//[7, 8]
    }



}
