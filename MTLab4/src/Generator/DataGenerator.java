package Generator;

import Generator.Helpers.Field;
import GrammarParser.grammarParserBaseListener;
import GrammarParser.grammarParserParser;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.*;

public class DataGenerator extends grammarParserBaseListener {
    public final static String EPSILON = "EPS";
    private final String END = "END";
    private final List<Pair> nonTerminals;
    private final List<PatternPair> terminals;
    private final Map<String, Product> grammarMap;
    private final Map<String, String> argsMap;
    private final Set<String> unitRules;
    private final Map<String, Set<String>> first, productsFirst, follow;
    private final List<Field> nodeFields;

    public DataGenerator() {
        this.nonTerminals = new ArrayList<>();
        this.terminals = new ArrayList<>();
        this.nodeFields = new ArrayList<>();
        this.first = new HashMap<>();
        this.follow = new HashMap<>();
        this.unitRules = new HashSet<>();
        this.argsMap = new HashMap<>();
        this.grammarMap = new HashMap<>();
        this.productsFirst = new HashMap<>();
    }

    private void rememberRule(final grammarParserParser.RContext ctx) {
        String name = ctx.RULE_NAME().getText();
        checkUniqueDeclare(name);
        var productsTermsContexts = ctx.products().productsTerms();
        grammarMap.put(name, new Product(productsTermsContexts));
        String s = "";
        if (ctx.PARAM() != null) {
            s = ctx.PARAM().getText();
            s = s.substring(1, s.length() - 1);
        }
        argsMap.put(name, s);
        nonTerminals.add(new Pair(name, productsTermsContexts, ctx.PARAM()));
        if (ctx.ret() != null) {
            String str = ctx.ret().getChild(1).getText();
            str = str.substring(1, str.length() - 1);
            String[] attrStr = str.split("\\s");
            Field attr = new Field(attrStr[1], attrStr[0], true);
            nodeFields.add(attr);
        }
    }

    private void initSet(final String name, Map<String, Set<String>> set) {
        Set<String> curSet = set.putIfAbsent(name, new HashSet<>());
        if (curSet == null) {
            curSet = first.get(name);
        }
        curSet.add(name);
    }

    private void setTerminalsFirstAndFollow(final grammarParserParser.TerminalContext ctx) {
        String name = ctx.TERMINAL_NAME().getText();
        checkUniqueDeclare(name);
        terminals.add(new PatternPair(ctx.terminalsThing().getText(), name));
        initSet(name, first);
    }

    private void generateFirst() {
        for (var p : nonTerminals) {
            first.put(p.name, new HashSet<>());
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (var pair : nonTerminals) {
                Set<String> curRulesFirst = first.get(pair.name);
                int size = curRulesFirst.size();
                List<grammarParserParser.ProductsTermsContext> products = pair.products;
                for (var tree : products) {
                    boolean prevHasEps = true;
                    for (var ctx : tree.prodTerm()) {
                        String curName = ctx.leftTerm().getText();
                        Set<String> curFirst = first.get(curName);
                        if (curFirst == null) {
                            curRulesFirst.add(EPSILON);
                            continue;
                        }
                        if (prevHasEps) {
                            curRulesFirst.addAll(curFirst);
                            if (!curFirst.contains(EPSILON)) {
                                prevHasEps = false;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (size != curRulesFirst.size()) {
                    changed = true;
                }
            }
        }
    }

    private void generateProductsFirst() {
        for (var iter : grammarMap.entrySet()) {
            var p = iter.getValue().products;
            for (var prod : p) {
                Set<String> curFirst = new HashSet<>();
                for (var term : prod.getTerms()) {
                    if (term.toString().equals("EPS")) continue;
                    curFirst.addAll(first.get(term.toString()));
                    if (!first.get(term.toString()).contains(EPSILON)) break;
                }
                productsFirst.put(prod.toString(), curFirst);
            }
        }
    }

    private void generateFollow() {
        for (var p : nonTerminals) {
            Set<String> set = new HashSet<>();
            set.add(END);
            follow.put(p.name, set);
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (var pair : nonTerminals) {
                List<grammarParserParser.ProductsTermsContext> products = pair.products;
                for (var tree : products) {
                    for (int pos = 0; pos < tree.getChildCount(); ++pos) {
                        String prodName = tree.getChild(pos).getChild(0).getText();
                        Set<String> curProdFollow = follow.get(prodName);
                        if (curProdFollow == null) continue;
                        int size = curProdFollow.size();
                        Set<String> gammaFirst = new HashSet<>();
                        for (int i = pos + 1; i < tree.getChildCount(); ++i) {
                            Set<String> f = first.get(tree.getChild(i).getChild(0).getText());
                            gammaFirst.addAll(f);
                            if (!f.contains(EPSILON)) break;
                        }
                        if (curProdFollow.contains(EPSILON)) {
                            curProdFollow.addAll(gammaFirst);
                        } else {
                            curProdFollow.addAll(gammaFirst);
                            curProdFollow.remove(EPSILON);
                        }
                        Set<String> curRuleFollow = follow.get(pair.name);
                        if (gammaFirst.isEmpty() || gammaFirst.contains(EPSILON)) {
                            curProdFollow.addAll(curRuleFollow);
                        }
                        if (size != curProdFollow.size()) {
                            changed = true;
                        }
                    }
                }
            }
        }
    }

    private void checkUniqueDeclare(final String str) {
        if (unitRules.contains(str)) {
            throw new RuntimeException("The rule " + str + " has been declared more than once");
        }
        unitRules.add(str);
    }

    public Map<String, Set<String>> getFirst() {
        return first;
    }

    public Map<String, String> getArgsMap() {
        return argsMap;
    }

    public List<Field> getNodeFields() {
        return nodeFields;
    }

    public Map<String, Set<String>> getProductsFirst() {
        return productsFirst;
    }

    public Map<String, Set<String>> getFollow() {
        return follow;
    }

    public Map<String, Product> getGrammarMap() {
        return grammarMap;
    }

    public List<Pair> getNonTerminals() {
        return nonTerminals;
    }

    public List<PatternPair> getTerminals() {
        return terminals;
    }

    @Override
    public void exitMyGrammar(grammarParserParser.MyGrammarContext ctx) {
        generateFirst();
        generateProductsFirst();
        generateFollow();
    }

    @Override
    public void enterTerminal(grammarParserParser.TerminalContext ctx) {
        setTerminalsFirstAndFollow(ctx);
    }

    @Override
    public void enterR(grammarParserParser.RContext ctx) {
        rememberRule(ctx);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
    }
}
