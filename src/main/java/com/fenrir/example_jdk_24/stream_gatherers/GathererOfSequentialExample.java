package com.fenrir.example_jdk_24.stream_gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GathererOfSequentialExample {


    public static void main(String[] args) {


        // 創建一個批次處理的 Gatherer
        Gatherer<Integer, List<Integer>, List<Integer>> batchGatherer =
                Gatherer.ofSequential(
                        // 初始狀態
                        ArrayList::new,

                        // 處理邏輯
                        (batch, element, downstream) -> {
                            batch.add(element);
                            if (batch.size() == 3) {
                                // 當批次大小達到3時，發送並清空
                                List<Integer> currentBatch = new ArrayList<>(batch);
                                batch.clear();
                                return downstream.push(currentBatch);
                            }
                            return true; // 繼續處理
                        },

                        // Finisher: 處理剩餘元素
                        (batch, downstream) -> {
                            if (!batch.isEmpty()) {
                                System.out.println("finish elements: " + batch); // 輸出: [[1, 2, 3], [4, 5, 6], [7, 8]]
                                downstream.push(new ArrayList<>(batch));
                            }
                        }
                );
// 使用範例
        List<List<Integer>> batches = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(batchGatherer)
                .toList();

        System.out.println(batches); // 輸出: [[1, 2, 3], [4, 5, 6], [7, 8]]
    }

}
