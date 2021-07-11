package Generator.Helpers;

public class Field {
    private final String name;
    private final String type;
    private final String genericType;
    private String defaultValue = "";
    private StringBuilder sb;
    private final boolean needPublic;

    public Field(final String name, final String type, boolean needPublic) {
        this(name, type, "", needPublic);
    }

    public Field(final String name, final String type) {
        this(name, type, false);
    }

    public Field(final String name, final String type, final String genericType, boolean needPublic) {
        this.needPublic = needPublic;
        this.name = name;
        this.type = type;
        this.genericType = genericType;
        this.sb = new StringBuilder();
    }

    public String getName() {
        return name;
    }

    public String getGenericType() {
        return genericType;
    }

    public String getType() {
        return type;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        if (sb.length() == 0) {
            if (needPublic) {
                sb.append("public ");
            }
            sb.append(type);
            if (!genericType.isEmpty()) {
                sb.append("<").append(genericType).append(">");
            }
            sb.append(" ").append(name);
            if (!defaultValue.isEmpty()) {
                sb.append("= ").append(defaultValue);
            }
        }
        return sb.toString();
    }
}
