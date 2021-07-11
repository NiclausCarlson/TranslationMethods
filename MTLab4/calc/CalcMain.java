import Generator.Helpers.Utils;
import generated.__Lexer;
import generated.__Node;
import generated.__Parser;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CalcMain {
    private final static Utils utl = new Utils();

    public static void printTree(__Node a, __Node parent, FileWriter fw) throws IOException {
        if (a._name != null) {
            if (parent != null) {
                String parentNode = utl.easyRename(parent._name) + "_" + parent.hashCode();
                String childNode = utl.easyRename(a._name) + "_" + a.hashCode();
                fw.write(parentNode + " -> " + childNode + ";\n");
                StringBuilder sb = new StringBuilder(childNode);
                if (!a.value.equals("END") && !a.value.isEmpty()) {
                    sb.append("\nval: ").append(a.value);
                } else {
                    sb.append("\neVal: ").append(a.eVal);
                    sb.append("\nsumVal: ").append(a.sumVal).append(";");
                    sb.append("\ntVal: ").append(a.tVal).append(";");
                    sb.append("\nmulVal: ").append(a.mulVal).append(";");
                    sb.append("\nfVal: ").append(a.fVal).append(";");
                }
                fw.write(childNode + "[label = \"" + sb + "\"]");

            }
        }
        for (__Node n : a.children) {
            printTree(n, a, fw);
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "2^2","3^3^3","2^2^2^2","2^(2^3)^2","(3^3)^3",
                "(2 + 3 + 2)^(2 + 2 + 1)^(1 + 1 + 1)",
                "1-2-3", "1",
                "99", "1+1",
                "(((1 + 1)))", "(2 + 2) * 2",
                "2 + 2 * 2", "5+5+(5+(5+5+5)*5)+5",
                "(1 + 2 * (1 + 2 + 3 * (1 + 3 + 4 * (1 + 2 + 3 + 4 + 5 * (1 + 2 + 3 + 4 + 5 + 6 * (1 + 2 + 3 + 4 + 5 + 6 + 7))))))",
                "2 / 1", "2 / 2", "2 / 3", "2 / 4",
                "100 / 4 / 8", "100 / 4 / 8 * 8", "8 * 100 / 4 / 8", "8 * 100 / 4 / 8 + 1",
                "1-1", "1-101", "2-2-2-2-2-2", "2-3+1*23-5", "2.4+3.5");
        int testIdx = 1;
        InputStream in = new ByteArrayInputStream(tests.get(testIdx).getBytes(StandardCharsets.UTF_8));
        __Lexer lexer = new __Lexer(in);
        __Parser parser = new __Parser(lexer);
        __Node a = parser.e();
        System.out.println("Expression: " + tests.get(testIdx));
        System.out.println(a.eVal);
        try (FileWriter fw = new FileWriter("graph")) {
            fw.write("digraph AST{\n");
            printTree(a, null, fw);
            fw.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
