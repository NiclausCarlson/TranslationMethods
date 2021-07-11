package Generator;

import java.io.Serializable;
import java.util.regex.Pattern;

public class PatternPair implements Serializable {
    public Pattern pattern;
    public String terminalsName;

    public PatternPair(final String regex, final String name) {
        this.pattern = Pattern.compile(regex.substring(1, regex.length() - 1));
        this.terminalsName = name;
    }
}