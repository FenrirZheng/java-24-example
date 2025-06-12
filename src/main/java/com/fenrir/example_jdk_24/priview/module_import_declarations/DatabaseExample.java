package com.fenrir.example_jdk_24.priview.module_import_declarations;

// 傳統多行匯入

import module java.logging;
public class DatabaseExample {

    private static final Logger logger = Logger.getLogger(DatabaseExample.class.getName());
    void main() {

        logger.info("!!??");
        // 現在可以直接使用 java.sql 模組中的所有類別
        System.out.println("模組匯入成功！");
    }
}