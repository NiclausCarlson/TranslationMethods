import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        if (args.length != 2 || !args[1].endsWith(".html")) {
//            System.err.println("Invalid arguments");
//            return;
//        }
        if (args.length != 1) {
            System.err.println("Invalid argument");
            return;
        }
        String p1, p2;
        try {
            int a = Integer.parseInt(args[0]);
            p1 = "tests/test" + a;
            p2 = "tests/test" + a + ".html";
        } catch (NumberFormatException ex) {
            System.err.println("Invalid argument");
            return;
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(p1))) {
            char[] buff = new char[256];
            int q = bf.read(buff);
            while (q != -1) {
                sb.append(Arrays.copyOfRange(buff, 0, q));
                q = bf.read(buff);
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
            return;
        }
        gLexer lexer = new gLexer(CharStreams.fromString(sb.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        gParser parser;
        try {
            parser = new gParser(tokens);
        } catch (RecognitionException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        ParseTree tree = parser.latex();
        ParseTreeWalker walker = new ParseTreeWalker();
        try (PrintWriter out = new PrintWriter(p2)) {
            walker.walk(new LatexListener(out), tree);
        } catch (IOException ex) {
            System.err.println("Can't write in file");
        } catch (RuntimeException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
