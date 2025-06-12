void main() throws IOException, URISyntaxException {

    var p = Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Main.class")).toURI());
    var classFile = ClassFile.of().parse(p);

    for (var field : classFile.fields()) {
        var flags = field.flags();
        System.out.println("flags:" + flags);
        if (flags.has(AccessFlag.STATIC) && flags.has(AccessFlag.FINAL)) {
            System.out.printf("static final field %s = ", field.fieldName());
            var value = field.attributes().stream()
                    .filter(ConstantValueAttribute.class::isInstance)
                    .map(ConstantValueAttribute.class::cast)
                    .findFirst()
                    .map(constant -> constant.constant().constantValue().toString())
                    .orElse("null");
            System.out.printf("%s%n", value);
        }
    }


    for (var method : classFile.methods()) {
        System.out.println("method Name :: " + method.methodName());
    }

}