package Generator.Helpers;

public class Utils {
    private StringBuilder rename(final String name) {
        StringBuilder newName = new StringBuilder();
        for (int idx = 0; idx < name.length(); ++idx) {
            char curChr = name.charAt(idx);
            if (Character.isLetterOrDigit(curChr)) {
                newName.append(curChr);
            } else if (curChr == '\'') {
                newName.append("Quote");
            } else if (curChr != '_') {
                throw new RuntimeException("Unexpected name: " + name);
            }
        }
        return newName;
    }

    public String anotherRename(final String name) {
        StringBuilder newName = new StringBuilder();
        for (int idx = 0; idx < name.length(); ++idx) {
            char curChr = name.charAt(idx);
            if (Character.isLetterOrDigit(curChr)) {
                newName.append(curChr);
            } else if (curChr == '\'') {
                newName.append("quote");
            } else if (curChr == '_') {
                newName.append("underscore");
            } else if (curChr == '+') {
                newName.append("plus");
            } else if (curChr == '-') {
                newName.append("minus");
            } else if (curChr == '*') {
                newName.append("asterisk");
            } else if( curChr =='\\'){
                newName.append("slash");
            }
        }
        return newName.toString();
    }

    public String easyRename(final String name) {
        return rename(name).toString();
    }

    public String renameClass(final String name) {
        return "__" + rename(name);
    }

    public String renameMethod(final String name) {
        return "_" + rename(name);
    }
}
