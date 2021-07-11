package Generator;

import Generator.Helpers.Clazz;
import Generator.Helpers.Field;
import Generator.Helpers.Method;
import Generator.Helpers.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static Generator.Helpers.Method.NameMode.EASY_RENAMED;

public class ClassesGenerator {
    private final String PATH;
    private final String PACKAGE = "generated";
    private final String MAIN_CLAZZ = "Node";
    private final String PARSER = "Parser";
    private final String LEXER = "Lexer";
    private final Utils utl = new Utils();

    public ClassesGenerator(final String path) {
        this.PATH = path;
    }

    private void write(final String str, final String path) throws IOException {
        try {
            Files.createDirectory(Paths.get(PATH));
        } catch (FileAlreadyExistsException ignored) {
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH + "/" + path + ".java"))) {
            bw.write(str);
        }
    }

    private void generateLexer() throws IOException {
        String name = "Lexer";
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("inputStream", "InputStream", true));
        fields.add(new Field("curChar", "int", true));
        fields.add(new Field("curToken", "String", true));
        fields.add(new Field("curStr", "String", true));
        fields.add(new Field("terminals", "List<PatternPair>", true));
        List<Method> methods = new ArrayList<>();
        methods.add(new Method("void", "nextChar",
                Collections.emptyList(), "if (curChar == -1) return;\n" +
                "try {\ncurChar = inputStream.read();\n} catch (IOException ex) {\n" +
                "System.err.println(\"Can't read next char\");\n}"));

        String code = " while (Character.isWhitespace(curChar)) {\n" +
                "            nextChar();\n" +
                "        }\n" +
                "        StringBuilder sb = new StringBuilder();\n" +
                "        PatternPair p;\n" +
                "        while ((p = getPair(sb.toString())) == null) {\n" +
                "            if (curChar == -1) {\n" +
                "                sb.append('$');\n" +
                "                break;\n" +
                "            }\n" +
                "            if (curChar != '\\0' && curChar != -2)\n" +
                "                sb.append(Character.toString(curChar));\n" +
                "            nextChar();\n" +
                "        }\n" +
                "        if (p != null) {\n" +
                "            PatternPair p1 = null, p2;\n" +
                "            StringBuilder sb_1 = new StringBuilder(sb);\n" +
                "            StringBuilder sb_2 = new StringBuilder(sb);\n" +
                "            if (curChar != -1) {\n" +
                "                sb_2.append(Character.toString(curChar));\n" +
                "                while ((p2 = getPair(sb_2.toString())) != null && curChar != -1) {\n" +
                "                    p1 = p2;\n" +
                "                    nextChar();\n" +
                "                    if (curChar != '\\0') {\n" +
                "                        sb_1 = new StringBuilder(sb_2);\n" +
                "                        if (curChar != -1)\n" +
                "                            sb_2.append(Character.toString(curChar));\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            if (p1 == null) {\n" +
                "                curToken = p.terminalsName;\n" +
                "                curStr = sb.toString();\n" +
                "            } else {\n" +
                "                curToken = p1.terminalsName;\n" +
                "                curStr = sb_1.toString();\n" +
                "            }\n" +
                "        } else {\n" +
                "            curToken = \"END\";\n" +
                "            curStr = \"END\";\n" +
                "        }";
        methods.add(new Method("void", "setNextToken",
                Collections.emptyList(), code));

        code = "for (PatternPair p : terminals) {\n" +
                "            Matcher matcher = p.pattern.matcher(str);\n" +
                "            if (matcher.matches()) {\n" +
                "                return p;\n" +
                "            }\n" +
                "        }\n" +
                "        return null;";
        methods.add(new Method("PatternPair", "getPair", new Field("str", "String"), code));
        Clazz clazz = new Clazz(name, fields, methods);
        clazz.addConstructor(new Field("inputStream", "InputStream"),
                "this.inputStream = inputStream;\n" +
                        "this.curChar = -2;\n" +
                        " this.terminals = new ArrayList<>();\n" +
                        getCollectionInitCode("terminals", "List<PatternPair>") +
                        "setNextToken();"
        );
        clazz.setPack(PACKAGE);
        clazz.setImports("import java.io.FileInputStream;\n" +
                "import java.io.IOException;\n" +
                "import java.io.InputStream;\n" +
                "import java.io.ObjectInputStream;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import Generator.PatternPair;\n" +
                "import java.util.regex.Matcher;\n");
        write(clazz.toString(), clazz.getName());
    }

    private StringBuilder getCollectionInitCode(final String fieldName, final String castType) {
        StringBuilder code = new StringBuilder();
        code.append("try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(\"")
                .append(PATH).append("/")
                .append(fieldName).append(".data\"")
                .append("))) {").append("this.")
                .append(fieldName)
                .append(" = (").append(castType).append(")ois.readObject();\n}")
                .append("catch (Exception ex) {\nSystem.out.println(ex.getMessage());\n}");
        return code;
    }

    private void generateInnerCode(StringBuilder sb, List<Product.ProductStruct.Term> terms) {
        for (Product.ProductStruct.Term term : terms) {
            if (Character.isUpperCase(term.toString().charAt(0))) {
                sb.append("consume(\"").append(term).append("\");");
                sb.append("res.addChild(new __Node(\"").append(term).append("\", curStr));");
                sb.append("curStr = lexer.curStr;");
            } else {
                sb.append("res.addChild(").append(utl.easyRename(term.toString())).append("(");
                if (term.attr1 != null) {
                    sb.append(term.attr1);
                }
                sb.append("));");
                sb.append("curStr = lexer.curStr;");
            }
            if (term.attr2 != null) {
                sb.append(term.attr2);
            }
        }
    }

    private StringBuilder generateHasEpsCode(final Map<String, Product> nonTerminals) {
        StringBuilder sb = new StringBuilder("switch(name) {\n");
        for (var i : nonTerminals.entrySet()) {
            sb.append("case \"").append(i.getKey()).append("\":\n");
            for (var j : i.getValue().products) {
                sb.append("if (EPS.equals(\"").append(j.str).append("\") || first.get(\"")
                        .append(j.str)
                        .append("\").contains(EPS)) return true;\n");
            }
            sb.append("return false;\n");
        }
        sb.append("default: throw new RuntimeException(\"Unknown token\");\n}\n");
        return sb;
    }

    private StringBuilder generateIf(final Map.Entry<String, Product> productEntry) {
        StringBuilder sb = new StringBuilder();
        var productions = productEntry.getValue().products;
        sb.append("if (checkFirstWithoutEps(curToken, \"")
                .append(productions.get(0).toString())
                .append("\")) {\n ");
        var terms = productions.get(0).terms;
        generateInnerCode(sb, terms);
        sb.append("\n}");
        String epsCode = "";
        for (int idx = 1; idx < productions.size(); ++idx) {
            if (productions.get(idx).toString().equals("EPS")) {
                if (productions.get(idx).getTerms().get(0).attr2 != null) {
                    epsCode = productions.get(idx).getTerms().get(0).attr2;
                }
                continue;
            }
            sb.append("else if (checkFirstWithoutEps(curToken, \"")
                    .append(productions.get(idx).toString())
                    .append("\")) {\n");
            terms = productions.get(idx).terms;
            generateInnerCode(sb, terms);
            sb.append("\n}");
        }
        sb.append("else if(").append("hasEps && follow.get(\"")
                .append(productEntry.getKey()).append("\").contains(curToken)) {\n")
                .append("res.addChild(new __Node(EPS, EPS));\n")
                .append(epsCode).append("\n}");
        sb.append("else {\nthrow new RuntimeException(\"Unexpected token \" + curToken);\n}\n");
        return sb;
    }

    private List<Method> generateParserMethods(final Map<String, Product> nonTerminals,
                                               final Map<String, String> argsMap) {
        List<Method> methods = new ArrayList<>();
        methods.add(new Method("void", "consume",
                new Field("token", "String"),
                "if(!token.equals(lexer.curToken)) " +
                        "{\nthrow new RuntimeException(\"Expected \" + lexer.curToken + \" but found \" + token);}\n" +
                        "lexer.setNextToken();"));
        methods.add(new Method("boolean", "checkFirstWithoutEps",
                Arrays.asList(new Field("str", "String"),
                        new Field("rule", "String")),
                "return first.get(rule).contains(str) && !str.equals(EPS);"));
        methods.add(new Method("boolean", "hasNext",
                new Field("name", "String"), generateHasEpsCode(nonTerminals).toString()));
        for (var pair : nonTerminals.entrySet()) {
            StringBuilder code = new StringBuilder("String curToken = lexer.curToken;\n")
                    .append("String curStr = lexer.curStr;\n")
                    .append("__Node res = new __Node(\"").append(pair.getKey()).append("\");\n")
                    .append("boolean hasEps = hasNext(\"").append(pair.getKey()).append("\");\n")
                    .append(generateIf(pair))
                    .append("return res;");
            methods.add(new Method("__Node", pair.getKey(),
                    argsMap.get(pair.getKey()), code.toString(), EASY_RENAMED));
        }
        return methods;
    }

    private void generateParser(final Map<String, Product> nonTerminals,
                                final Map<String, String> argsMap) throws IOException {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("lexer", utl.renameClass("Lexer"), true));
        fields.add(new Field("EPS", "String", true));
        fields.add(new Field("first", "Map<String, Set<String>>", true));
        fields.add(new Field("follow", "Map<String, Set<String>>", true));

        List<Method> methods = generateParserMethods(nonTerminals, argsMap);
        Clazz parser = new Clazz(PACKAGE, PARSER, fields, methods);

        StringBuilder inits = getCollectionInitCode("first", "Map<String, Set<String>>");
        inits.append(getCollectionInitCode("follow", "Map<String, Set<String>>"))
                .append("this.lexer = lexer;\n")
                .append("this.EPS = \"EPS\";");
        parser.addConstructor(new Field("lexer", utl.renameClass(LEXER)), inits.toString());
        parser.setImports("import java.io.FileInputStream;\n" +
                "import java.io.ObjectInputStream;\n" +
                "import java.util.*;\n" +
                "import java.util.stream.Collectors;\n");
        write(parser.toString(), parser.getName());
    }

    private void generateNode(final List<Field> nodeFields) throws IOException {
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("_name", "String", true));
        fields.add(new Field("value", "String", true));
        fields.addAll(nodeFields);
        List<Method> methods = Collections.singletonList(
                new Method("void", "addChild",
                        new Field("child", "__Node"),
                        "\tthis.children.add(child);"));
        Clazz node = new Clazz(PACKAGE, utl.renameClass(MAIN_CLAZZ), fields, methods);
        node.setImports("import java.util.List;\n" +
                "import java.util.ArrayList;\n");
        node.setChildren(new Field("children", "List", utl.renameClass(MAIN_CLAZZ), true));
        node.addConstructor(Collections.emptyList(), "this(\"\", \"\");");
        node.addConstructor(new Field("name", "String"), "this(name, \"\");");
        node.addConstructor(Arrays.asList(new Field("name", "String"),
                new Field("value", "String")),
                "this.children = new ArrayList<>();\nthis._name = name;\nthis.value = value;");
        write(node.toString(), node.getName());
    }

    public void generate(final List<Field> nodeFields,
                         final Map<String, Product> nonTerminals,
                         final Map<String, String> argsMap) throws IOException {
        generateNode(nodeFields);
        generateLexer();
        generateParser(nonTerminals, argsMap);
    }
}
