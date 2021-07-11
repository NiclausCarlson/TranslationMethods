import Exceptions.KotlinFunParserException;
import Parser.Parser;
import Parser.Tree;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        String test = "fun efe(b:E, c:Q<DDT>, t:Q):Ddfs<ETFfvdf>";
        InputStream in = new ByteArrayInputStream(test.getBytes(StandardCharsets.UTF_8));
        Tree tree;
        try {
            tree = parser.parse(in);
            System.out.println(tree.toString());
        } catch (KotlinFunParserException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("graph"))){
            tree.printGraph(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
