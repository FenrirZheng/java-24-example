package com.fenrir.example_jdk_24.priview.primitive_types_in_patterns;

public class main {

    public static void main(String[] args) {
        // instanceof 與原始型別
        float value = 3.5f;
        if (value instanceof int i) {
            System.out.println("可以安全轉換為 int: " + i);
        } else {
            System.out.println("不是int");
        }

// switch 支援更多原始型別
        long number = 100L;
        switch (number) {
            case 50L -> System.out.println("五十");
            case 100L -> System.out.println("一百");
            default -> System.out.println("其他數字");
        }

// 原始型別模式匹配
        int v = 20;
        switch (v) {
            case int i when i < 20 -> System.out.println("數值太小: " + i);
            case int i -> System.out.println("完美的數值: " + i);
        }
    }
}
