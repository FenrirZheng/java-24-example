
record Shape(int x, int y) {
}

record ScaledShape(Shape shape, double scale) {
}

void main() {
    var scaledShape = new ScaledShape(new Shape(1, 2), 0.5);
    // Java 24 中可以這樣寫
    switch (scaledShape) {
        case ScaledShape(Shape s, byte scale) -> System.out.println(s + "-> 小比例: " + scale);
        case ScaledShape(Shape s, int scale) -> System.out.println(s + "-> 中等比例: " + scale);
        case ScaledShape(Shape s, double scale) -> System.out.println(s + "-> 完整精度: " + scale);
    }
}