package Generator.Helpers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Method {
    private final String returnType;
    private final String name;
    private final List<Field> argv;
    private final String strArgv;
    private String body;
    private final StringBuilder sb;

    public Method(final String returnType, final String name, final String argv, final String body, final NameMode mode) {
        Utils utl = new Utils();
        this.returnType = returnType;
        switch (mode) {
            case CONSTRUCTOR:
                this.name = utl.renameClass(name);
                break;
            case RENAMED_METHOD:
                this.name = utl.renameMethod(name);
                break;
            case EASY_RENAMED:
                this.name = utl.easyRename(name);
                break;
            default:
                this.name = name;
        }
        this.strArgv = argv;
        this.argv = null;
        this.body = body;
        this.sb = new StringBuilder();
    }

    public Method(final String returnType, final String name, final List<Field> argv, final String body, final NameMode mode) {
        Utils utl = new Utils();
        this.returnType = returnType;
        switch (mode) {
            case CONSTRUCTOR:
                this.name = utl.renameClass(name);
                break;
            case RENAMED_METHOD:
                this.name = utl.renameMethod(name);
                break;
            case EASY_RENAMED:
                this.name = utl.easyRename(name);
                break;
            default:
                this.name = name;
        }
        this.argv = argv;
        this.strArgv = null;
        this.body = body;
        this.sb = new StringBuilder();
    }

    public Method(final String returnType, final String name, final Field arg, final String body, final NameMode mode) {
        this(returnType, name, Collections.singletonList(arg), body, mode);
    }

    public Method(final String returnType, final String name, final List<Field> argv, final String body) {
        this(returnType, name, argv, body, NameMode.EASY_RENAMED);
    }

    public Method(final String returnType, final String name, final Field arg, final String body) {
        this(returnType, name, Collections.singletonList(arg), body, NameMode.EASY_RENAMED);
    }

    public Method(final String returnType, final String name, final List<Field> argv) {
        this(returnType, name, argv, "", NameMode.EASY_RENAMED);
    }

    public String getName() {
        return name;
    }

    public List<Field> getArgv() {
        return argv;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        if (sb.length() == 0) {
            sb.append("public ");
            if (!returnType.isEmpty()) {
                sb.append(returnType)
                        .append(" ");
            }
            sb.append(name);
            if (argv == null) {
                sb.append('(').append(strArgv).append(')');
            } else {
                sb.append(argv.stream()
                        .map(Field::toString)
                        .collect(Collectors.joining(", ", "(", ")")));
            }
            sb.append(" {\n")
                    .append(body)
                    .append("\n}");
        }
        return sb.toString();
    }

    public enum NameMode {
        CONSTRUCTOR,
        RENAMED_METHOD,
        EASY_RENAMED,
        NOT_RENAMED_METHOD
    }
}
