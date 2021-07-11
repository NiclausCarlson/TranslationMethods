package generated;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import Generator.PatternPair;
import java.util.regex.Matcher;


public class __Lexer {
public InputStream inputStream;
public int curChar;
public String curToken;
public String curStr;
public List<PatternPair> terminals;

public __Lexer(InputStream inputStream) {
this.inputStream = inputStream;
this.curChar = -2;
 this.terminals = new ArrayList<>();
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("calc/generated/terminals.data"))) {this.terminals = (List<PatternPair>)ois.readObject();
}catch (Exception ex) {
System.out.println(ex.getMessage());
}setNextToken();
}

public void nextChar() {
if (curChar == -1) return;
try {
curChar = inputStream.read();
} catch (IOException ex) {
System.err.println("Can't read next char");
}
}
public void setNextToken() {
 while (Character.isWhitespace(curChar)) {
            nextChar();
        }
        StringBuilder sb = new StringBuilder();
        PatternPair p;
        while ((p = getPair(sb.toString())) == null) {
            if (curChar == -1) {
                sb.append('$');
                break;
            }
            if (curChar != '\0' && curChar != -2)
                sb.append(Character.toString(curChar));
            nextChar();
        }
        if (p != null) {
            PatternPair p1 = null, p2;
            StringBuilder sb_1 = new StringBuilder(sb);
            StringBuilder sb_2 = new StringBuilder(sb);
            if (curChar != -1) {
                sb_2.append(Character.toString(curChar));
                while ((p2 = getPair(sb_2.toString())) != null && curChar != -1) {
                    p1 = p2;
                    nextChar();
                    if (curChar != '\0') {
                        sb_1 = new StringBuilder(sb_2);
                        if (curChar != -1)
                            sb_2.append(Character.toString(curChar));
                    }
                }
            }
            if (p1 == null) {
                curToken = p.terminalsName;
                curStr = sb.toString();
            } else {
                curToken = p1.terminalsName;
                curStr = sb_1.toString();
            }
        } else {
            curToken = "END";
            curStr = "END";
        }
}
public PatternPair getPair(String str) {
for (PatternPair p : terminals) {
            Matcher matcher = p.pattern.matcher(str);
            if (matcher.matches()) {
                return p;
            }
        }
        return null;
}
}