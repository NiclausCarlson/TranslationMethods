package Generator;

import GrammarParser.grammarParserParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Pair {
    String name;
    List<grammarParserParser.ProductsTermsContext> products;
    TerminalNode param;
    grammarParserParser.TerminalsThingContext terminal;

    Pair(String name, List<grammarParserParser.ProductsTermsContext> products, TerminalNode param) {
        this.name = name;
        this.products = products;
        this.terminal = null;
        this.param = param;
    }

    Pair(String name, grammarParserParser.TerminalsThingContext product) {
        this.name = name;
        this.terminal = product;
        this.products = null;
        this.param = null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        assert terminal != null;
        String str = terminal.leftThing().getText();
        if (str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }
}
