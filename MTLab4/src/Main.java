import Generator.ClassesGenerator;
import Generator.DataGenerator;
import Generator.PatternPair;
import GrammarParser.grammarParserLexer;
import GrammarParser.grammarParserParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.*;

public class Main {
    private static void print(final Map<String, Set<String>> map, final String str) {
        System.out.println(str + ":");
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private static class Serializer<T> {
        public void serialize(final T data, final String path) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
                oos.writeObject(data);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String[] paths = {"calc/generated", "secondHW/generated"};
        String[] grammars = {"grammar/Calc", "grammar/KotlinGrammar"};
        int idx = 0;
        String path = paths[idx];
        String p1 = grammars[idx];
        StringBuilder sb = new StringBuilder();
        try (var bf = new BufferedReader(new FileReader(p1))) {
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
        var lexer = new grammarParserLexer(CharStreams.fromString(sb.toString()));
        var tokens = new CommonTokenStream(lexer);
        grammarParserParser parser;
        try {
            parser = new grammarParserParser(tokens);
        } catch (RecognitionException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        var tree = parser.myGrammar();
        var walker = new ParseTreeWalker();
        var generator = new DataGenerator();
        walker.walk(generator, tree);
        var first = generator.getProductsFirst();
        var follow = generator.getFollow();
        var nodeFields = generator.getNodeFields();
        var grammarMap = generator.getGrammarMap();
        var terminals = generator.getTerminals();
        var argsMap = generator.getArgsMap();
//        print(first, "First");
//        print(follow, "\nFollow");

        ClassesGenerator classesGenerator = new ClassesGenerator(path);
        try {
            classesGenerator.generate(nodeFields, grammarMap, argsMap);
        } catch (IOException ex) {
            System.err.println("Can't create classes.");
        }

        Serializer<Map<String, Set<String>>> mapSetSerializer = new Serializer<>();
        mapSetSerializer.serialize(first, path + "/first.data");
        mapSetSerializer.serialize(follow, path + "/follow.data");

        Serializer<List<PatternPair>> listSerializer = new Serializer<>();
        listSerializer.serialize(terminals, path + "/terminals.data");
    }
}
