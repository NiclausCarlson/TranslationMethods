import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.PrintWriter;
import java.util.*;

public class LatexListener extends gBaseListener {
    private final PrintWriter writer;

    private final Set<String> EXCESS = Set.of("\\begin{enumerate}", "\\begin{itemize}", "\\end{enumerate}", "\\end{itemize}", "\\item", "$", "$$");

    private final Map<String, String> MATH_SYMBOLS;

    private String toHtmlStyle(String str) {
        String value = MATH_SYMBOLS.getOrDefault(str, "");
        if (!value.isEmpty()) {
            return value;
        }
        if (str.startsWith("\\")) {
            return "&" + str.substring(1) + ";";
        }
        if (str.startsWith("_") || str.startsWith("^")) {
            return str.substring(1);
        }
        if (str.equals("{") || str.equals("}")) {
            return "";
        }
        return str;
    }

    public LatexListener(PrintWriter out) {
        this.writer = out;
        this.MATH_SYMBOLS = new HashMap<>();
        MATH_SYMBOLS.put("\\neg", "&not;");
        MATH_SYMBOLS.put("\\land", "&and;");
        MATH_SYMBOLS.put("\\lor", "&or;");
        MATH_SYMBOLS.put("\\forall", "&forall;");
        MATH_SYMBOLS.put("\\exists", "&exist;");
        MATH_SYMBOLS.put("\\leq", "&le;");
        MATH_SYMBOLS.put("\\geq", "&ge;");
        MATH_SYMBOLS.put("\\subset", "&sub;");
        MATH_SYMBOLS.put("\\subseteq", "&sube;");
        MATH_SYMBOLS.put("\\in", "&isin;");
        MATH_SYMBOLS.put("\\emptyset", "&empty;");
        MATH_SYMBOLS.put("\\cup", "&cup;");
        MATH_SYMBOLS.put("\\cap", "&cap;");
        MATH_SYMBOLS.put("\\setminus", "&#8726;");
        MATH_SYMBOLS.put("\\Rightarrow", "&rarr;");
        MATH_SYMBOLS.put("\\Leftarrow", "&larr;");
    }

    @Override
    public void enterText(gParser.TextContext ctx) {
        RuleContext context = ctx.parent;
        if (!(context instanceof gParser.ItemsContext) && ctx.getChildCount() != 0) {
            writer.write("<pre>");
        }
    }

    @Override
    public void exitText(gParser.TextContext ctx) {
        RuleContext context = ctx.parent;
        if (!(context instanceof gParser.ItemsContext && ctx.getChildCount() != 0)) {
            writer.write("</pre>\n");
        }
    }

    @Override
    public void enterSimple_formula(gParser.Simple_formulaContext ctx) {
        writer.write("<p> <span class=\"formula\">");
    }

    @Override
    public void exitSimple_formula(gParser.Simple_formulaContext ctx) {
        writer.write("</span> </p>\n");
    }

    @Override
    public void enterNewline_formula(gParser.Newline_formulaContext ctx) {
        writer.write("<p class=margined> <span class=\"formula\">");
    }

    @Override
    public void exitNewline_formula(gParser.Newline_formulaContext ctx) {
        writer.write("</span> </p>\n");
    }

    public void enterProxi(gParser.ProxiContext ctx) {
        writer.write("<span>");

    }
    public void exitProxi(gParser.ProxiContext ctx) {
        writer.write("</span>");

    }

    @Override
    public void enterUpper_or_lower(gParser.Upper_or_lowerContext ctx) {
//        writer.write("<span>");
    }

    @Override
    public void exitUpper_or_lower(gParser.Upper_or_lowerContext ctx) {
//        writer.write("</span>");
    }

    @Override
    public void enterUppers(gParser.UppersContext ctx) {
        writer.write(" <sup> ");
    }

    @Override
    public void exitUppers(gParser.UppersContext ctx) {
        writer.write(" </sup> ");
    }

    @Override
    public void enterLowers(gParser.LowersContext ctx) {
        writer.write(" <sub> ");
    }

    @Override
    public void exitLowers(gParser.LowersContext ctx) {
        writer.write(" </sub> ");
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (!EXCESS.contains(node.getText())) {
            writer.write(toHtmlStyle(node.getText()));
        }
    }

    @Override
    public void enterEnumerate(gParser.EnumerateContext ctx) {
        writer.write("<ol>");
    }

    @Override
    public void exitEnumerate(gParser.EnumerateContext ctx) {
        writer.write("</ol>\n");
    }

    @Override
    public void enterItemize(gParser.ItemizeContext ctx) {
        writer.write("<ul>\n");
    }

    @Override
    public void exitItemize(gParser.ItemizeContext ctx) {
        writer.write("</ul>\n");
    }

    @Override
    public void enterItems(gParser.ItemsContext ctx) {
        writer.write("<li>");
    }

    @Override
    public void exitItems(gParser.ItemsContext ctx) {
        writer.write("</li>\n");
    }

    @Override
    public void enterLatex(gParser.LatexContext ctx) {
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<style> p.margined {margin-left: 10%;}\n" +
                ".formula span{\n" +
                "    display: inline-block;\n" +
                "}\n" +
                ".formula span sup, .formula span sub {\n" +
                "    display: block;\n" +
                "    font-size: 65%;\n" +
                "    line-height: 0;\n" +
                "    position: relative;\n" +
                "    vertical-align: baseline;\n" +
                "}\n" +
                ".formula span sup {\n" +
                "    top: -0.6em;\n" +
                "}\n" +
                ".formula span sub {\n" +
                "    top: 0.4em;\n" +
                "}" +
                "\n</style>" +
                "</head>\n" +
                "<body>\n");
    }

    @Override
    public void exitLatex(gParser.LatexContext ctx) {
        writer.write("</body>\n" +
                "</html>");
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        throw new RuntimeException("visited");
    }
}
