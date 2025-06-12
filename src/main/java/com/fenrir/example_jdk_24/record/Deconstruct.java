
// 目前不能使用...
//[Incubating] Problems report is available at: file:///home/fenrir/code/my-github/jdk-24-example/build/reports/problems/problems-report.html
void main() {
    record JsonNumber(double value) {
    }

    JsonNumber obj = new JsonNumber(1.2);

// 現在可以安全轉換原始型別
    if (obj instanceof JsonNumber(int i)) {
        // 如果 double 值可以安全轉換為 int，這個模式會匹配
        System.out.println("整數值: " + i);
    }

    if (obj instanceof JsonNumber(byte b)) {
        // 如果值在 byte 範圍內，這個模式會匹配
        System.out.println("位元組值: " + b);
    }
}