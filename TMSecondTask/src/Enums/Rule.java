package Enums;

public enum Rule {
    FUNCTION("FUNCTION"),
    BODY("BODY"),
    ARGUMENTS_1("ARGUMENTS_1"),
    ARGUMENTS_2("ARGUMENTS_2"),
    ARGUMENTS_3("ARGUMENTS_3"),
    ARGUMENT("ARGUMENT"),
    RETURN_TYPE("RETURN_TYPE"),
    NAME("NAME"),
    TYPE("TYPE"),
    GENERIC("GENERIC"),
    SIMPLE_TYPE("SIMPLE_TYPE"),
    EPS("");
    String name;

    Rule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
