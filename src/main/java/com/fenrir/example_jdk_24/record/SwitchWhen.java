
void main() {
    record Temperature(double celsius) {
    }
    record WeatherData(Temperature temp, int humidity) {
    }

    var weatherData = new WeatherData(new Temperature(-10), 100);
// 可以直接轉換並檢查範圍
    switch (weatherData) {
        case WeatherData(Temperature(int c), int h) when c < 0 -> System.out.println("結冰天氣");
        case WeatherData(Temperature(int c), int h) when c > 30 -> System.out.println("炎熱天氣");
        case WeatherData(Temperature(double c), int h) -> System.out.println("一般天氣: " + c + "°C");
    }

    // 金融資料處理
    var t = new Transaction(new Price(101, "CNY"), "現金");
    var c = categorizeTransaction(t);
    System.out.println(c);

    System.out.println("---");
    // 配置資料驗證
    var config = new DatabaseConfig("localhost", 12345, true);
    validateConfig(config);
}

record Price(double amount, String currency) {
}

record Transaction(Price price, String type) {
}

public static String categorizeTransaction(Transaction tx) {
    return switch (tx) {
        // 小額交易（可以安全轉換為 int）
        case Transaction(Price(int amount, String curr), String type)
                when amount < 100 -> "小額" + type;

        // 大額交易
        case Transaction(Price(double amount, String curr), String type)
                when amount >= 10000 -> "大額" + type;

        // 一般交易
        case Transaction(Price(double amount, String curr), String type) -> "一般 " + amount + ", 種類: " + type;
    };
}


// ---
record DatabaseConfig(String host, double port, boolean ssl) {
}

public static void validateConfig(DatabaseConfig config) {
    switch (config) {
        // 標準埠號（可轉換為 int）
        case DatabaseConfig(String h, int p, boolean ssl)
                when p > 0 && p <= 65535 -> System.out.println("有效配置: " + h + ":" + p);

        // 無效的埠號
        case DatabaseConfig(String h, double p, boolean ssl) -> throw new IllegalArgumentException("無效埠號: " + p);
    }
}