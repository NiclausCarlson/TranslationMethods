import generated.__Lexer;
import generated.__Node;
import generated.__Parser;
import Generator.Helpers.Utils;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class SecondHWMain {
    private final static Utils utl = new Utils();

    public static void printTree(__Node a, __Node parent, FileWriter fw) throws IOException {
        if (a._name != null) {
            if (parent != null) {
                String parentNode = utl.easyRename(parent._name) + "_" + parent.hashCode();
                String childNode = utl.easyRename(a._name) + "_" + a.hashCode();
                fw.write(parentNode + " -> " + childNode + ";\n");
                if (!a.value.equals("END") && !a.value.isEmpty()) {
                    StringBuilder sb = new StringBuilder(childNode);
                    sb.append("\nval: ").append(a.value);
                    fw.write(childNode + "[label = \"" + sb + "\"]");
                }
            }
        }
        for (__Node n : a.children) {
            printTree(n, a, fw);
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList("fun f()",
                "fun fUncTeiOOn111()",
                "fun fUnciOOn111(): UnitTest",
                "fun gUncTein111(a:Integer, b: NeIntegerADouble)",
                "fun fUncTeiOOn111(a:Integer, b: NeIntegerADouble): Pair",
                "fun f(a:Integer): Integer",
                "fun anotherFunction(a:Integer,a:Integer,a:Integer,a:Integer,a:Integer): Unit",
                "fun _fUncTeiOOn111()",
                "fun __fUncTeiOOn111()");
        int testIdx = 3;
        InputStream in = new ByteArrayInputStream(tests.get(testIdx).getBytes(StandardCharsets.UTF_8));
        __Lexer lexer = new __Lexer(in);
        __Parser parser = new __Parser(lexer);
        __Node a = parser.function();
        System.out.println("Expression: " + tests.get(testIdx));
        try (FileWriter fw = new FileWriter("graph")) {
            fw.write("digraph AST{\n");
            printTree(a, null, fw);
            fw.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
