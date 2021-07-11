package Parser;

import Enums.Rule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tree {
    private List<Tree> children;
    private String node;
    private final Rule rule;

    public Tree(Rule rule) {
        this.rule = rule;
    }

    public Tree(Rule rule, Tree... children) {
        this.rule = rule;
        this.children = Arrays.asList(children);
    }

    public Tree(Rule rule, String node, Tree... children) {
        this(rule, children);
        this.node = node;
    }

    public Tree(Rule rule, List<Tree> children) {
        this.rule = rule;
        this.children = children;
    }

    public String toString() {
        switch (rule) {
            case FUNCTION:
                return "fun " + children.get(0).toString();
            case BODY:
                return children.get(0).toString() +
                        children.get(1).toString() +
                        children.get(2).toString();
            case NAME:
            case SIMPLE_TYPE:
                return node;
            case TYPE:
                if (children.size() == 2) return children.get(0).toString() + children.get(1).toString();
                else if (children.size() == 1) return children.get(0).toString();
                return node;
            case GENERIC:
                if (node != null) return "<" + node + ">";
                return "";
            case ARGUMENTS_1:
                return "(" + children.get(0).toString() + ")";
            case ARGUMENTS_2:
                return children.get(0).toString() + children.get(1).toString();
            case ARGUMENTS_3:
                if (children.isEmpty()) return "";
                return children.stream().map(a -> a.toString()).collect(Collectors.joining(", ", ", ", ""));
            case ARGUMENT:
                return children.get(0).toString() + ":" + children.get(1).toString();
            case RETURN_TYPE:
                String type = children.get(0).toString();
                if (type.equals("")) return "";
                return ": " + type;
            default:
                return rule.getName();
        }
    }

    public void printGraph(BufferedWriter fw) throws IOException {
        Map<Rule, Integer> counter = new HashMap<>();
        counter.put(Rule.FUNCTION, 0);
        counter.put(Rule.BODY, 0);
        counter.put(Rule.ARGUMENTS_1, 0);
        counter.put(Rule.ARGUMENTS_2, 0);
        counter.put(Rule.ARGUMENTS_3, 0);
        counter.put(Rule.ARGUMENT, 0);
        counter.put(Rule.RETURN_TYPE, 0);
        counter.put(Rule.NAME, 0);
        counter.put(Rule.TYPE, 0);
        counter.put(Rule.GENERIC, 0);
        counter.put(Rule.SIMPLE_TYPE, 0);
        counter.put(Rule.EPS, 0);
        fw.write("digraph AST {");
        fw.newLine();
        printGraph(fw, "", counter);
        fw.write("}");
    }

    private void printGraph(BufferedWriter fw, final String cur, Map<Rule, Integer> counter) throws IOException {
        String u = cur;
        if (cur.equals("")) {
            u = getAndUpdate(rule, counter);
        }
        if (rule == Rule.FUNCTION) {
            fw.write(rule.getName() + " -> " + "fun;");
        }
        if (rule == Rule.EPS) return;
        for (Tree ch : children) {
            String v = getAndUpdate(ch.rule, counter);
            if (ch.rule == Rule.NAME || ch.rule == Rule.TYPE) {
                if(ch.node != null) {
                    fw.write(v + " -> " + ch.node + ";");
                    fw.newLine();
                }
            }
            fw.write(u + " -> " + v + ";");
            fw.newLine();
            ch.printGraph(fw, v, counter);
        }
    }

    private String getAndUpdate(Rule rule, Map<Rule, Integer> counter) {
        Integer ruleCounter = counter.get(rule);
        String result;
        if (ruleCounter == 0) {
            result = rule.getName();
        } else {
            result = rule.getName() + "_" + counter.get(rule);
        }
        counter.put(rule, ruleCounter + 1);
        return result;
    }
}
