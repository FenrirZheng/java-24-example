package com.fenrir.example_jdk_24.stream_gatherers;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class OfGreedy {

    public static void main(String[] args) {

// 自訂 Gatherer 範例
        Stream.of(1, 2, 3, 4, 5, 6)
                .gather(scan(() -> "", (string, number) -> string + number))

                .forEach(System.out::println);
// output:
//1
//12
//123
//1234
//12345
//123456
    }


    /**
     * ~~
     *
     * <p>
     * {@snippet lang = Markdown:
     * # 這個 ofGreedy 接收三個參數：
     * state: 當前狀態（在這裡是 State 物件，包含累積結果）
     * element: 當前處理的輸入元素
     * downstream: 下游處理器，用於推送結果
     * ## 執行流程
     * 1. 以 Stream.of(1, 2, 3, 4, 5, 6) 為例：
     * 初始化: state.current = "" （空字串）
     * 處理元素 1:
     * 2. state.current = "" + 1 = "1"
     * 推送 "1" 到下游
     * 處理元素 2:
     * 3. state.current = "1" + 2 = "12"
     * 推送 "12" 到下游
     * 依此類推...
     *}
     * </p>
     *
     * @param initial
     * @param scanner
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Gatherer<T, ?, R> scan(
            Supplier<R> initial,
            BiFunction<? super R, ? super T, ? extends R> scanner) {

        class State {
            R current = initial.get();
        }

        return Gatherer.ofSequential(
                State::new,  // initializer

//ofGreedy 表示這個 Integrator 會貪婪地處理每個輸入元素，也就是說：
//每接收到一個輸入元素，就立即處理並可能產生輸出
//不會等待或緩存多個元素再處理
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    state.current = scanner.apply(state.current, element);
                    return downstream.push(state.current);
                })
// 補充說明:
//Greedy: 每個輸入元素都會立即產生一個輸出
//Non-Greedy: 可能會累積多個輸入元素後才產生輸出，或者一個輸入產生多個輸出
        );
    }


}
