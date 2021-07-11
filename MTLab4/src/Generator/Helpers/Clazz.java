package Generator.Helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Clazz {
    private final String mod = "public";
    private String pack = "";
    private String extend = "";
    private String imports = "";
    private List<Method> constructors;
    private final String name;

    private Field children;
    private final List<Field> fields;
    private final List<Method> methods;

    private StringBuilder sb;

    public Clazz(final String pack, final String name, final List<Field> fields, final List<Method> methods) {
        Utils utl = new Utils();
        this.pack = pack;
        this.name = utl.renameClass(name);
        this.fields = fields;
        this.methods = methods;
        this.constructors = new ArrayList<>();
        this.sb = new StringBuilder();
    }

    public Clazz(final String name, final List<Field> fields, final List<Method> methods) {
        this("", name, fields, methods);
    }

    public String getName() {
        return name;
    }

    public Field getChildren() {
        return children;
    }

    public void setChildren(final Field children) {
        this.children = children;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public String getMod() {
        return mod;
    }

    public String getPack() {
        return pack;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public void setPack(final String pack) {
        this.pack = pack;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public void addConstructor(final List<Field> args, final String body) {
        constructors.add(new Method("", "_" + this.name, args, body, Method.NameMode.CONSTRUCTOR));
    }

    public void addConstructor(final Field arg, final String body) {
        constructors.add(new Method("", "_" + this.name, Collections.singletonList(arg), body, Method.NameMode.CONSTRUCTOR));
    }

    @Override
    public String toString() {
        if (sb.length() == 0) {
            if (!pack.isEmpty()) {
                sb.append("package").append(" ").append(pack).append(";\n\n");
            }
            if (!imports.isEmpty()) {
                sb.append(imports).append("\n\n");
            }
            sb.append(mod).append(" class ").append(name);
            if (!extend.isEmpty()) {
                sb.append(" extends ").append(extend);
            }
            sb.append(" {\n");
            if (children != null) {
                sb.append(children).append(";\n");
            }
            if (!fields.isEmpty()) {
                sb.append(fields.stream()
                        .map(Field::toString)
                        .collect(Collectors.joining(";\n", "", ";\n\n")));
            }
            if (!constructors.isEmpty()) {
                for (Method constructor : constructors) {
                    sb.append(constructor).append("\n");
                }
            }
            sb.append("\n").append(methods.stream()
                    .map(Method::toString)
                    .collect(Collectors.joining("\n")))
                    .append("\n}");
        }
        return sb.toString();
    }

}
