package Generator;

import GrammarParser.grammarParserParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Product implements Serializable {
    public static class ProductStruct {
        public static class Term {
            public String term;
            public String attr1;
            public String attr2;

            private String renameAttr(final String attr) {
                StringBuilder newStr = new StringBuilder();
                for (int idx = 0; idx < attr.length(); ++idx) {
                    if (attr.charAt(idx) == '$' && (idx + 1 < attr.length() && attr.charAt(idx + 1) == '_')) {
                        newStr.append("res.children.get(");
                        idx ++;
                        if (idx >= attr.length()) {
                            newStr.append(attr.substring(idx));
                            break;
                        }
                        char prevCh = attr.charAt(idx++);
                        if (idx >= attr.length()) {
                            newStr.append(attr.substring(idx));
                            break;
                        }
                        char curCh = attr.charAt(idx);
                        while (prevCh != '_' | !Character.isDigit(curCh)) {
                            ++idx;
                            if (idx >= attr.length()) {
                                newStr.append(attr.substring(idx));
                                return newStr.toString();
                            }
                            prevCh = curCh;
                            curCh = attr.charAt(idx);
                        }
                        while (Character.isDigit(curCh)) {
                            newStr.append(curCh);
                            ++idx;
                            if (idx >= attr.length()) {
                                newStr.append(attr.substring(idx));
                                return newStr.toString();
                            }
                            curCh = attr.charAt(idx);
                        }
                        newStr.append("-1)").append(curCh);
                    } else {
                        newStr.append(attr.charAt(idx));
                    }
                }
                return newStr.toString();
            }

            public Term(final String term, final String attr1, final String attr2) {
                this.term = term;
                this.attr1 = attr1;
                if (this.attr1 != null) {
                    this.attr1 = this.attr1.substring(1, this.attr1.length() - 1);
                    this.attr1 = renameAttr(this.attr1);
                }
                this.attr2 = attr2;
                if (this.attr2 != null) {
                    this.attr2 = this.attr2.substring(1, this.attr2.length() - 1);
                    this.attr2 = renameAttr(this.attr2);
                }
            }

            @Override
            public String toString() {
                return term;
            }
        }

        public final List<Term> terms;
        public String str;

        public ProductStruct(final grammarParserParser.ProductsTermsContext ctx) {
            this.terms = new ArrayList<>();
            for (var child : ctx.prodTerm()) {
                String attr1 = null;
                String attr2 = null;
                if (child.rightTerm() != null) {
                    if (child.rightTerm().PARAM() != null)
                        attr1 = child.rightTerm().PARAM().getText();
                    if (child.rightTerm().ATTR() != null)
                        attr2 = child.rightTerm().ATTR().getText();
                }
                terms.add(new Term(child.leftTerm().getText(), attr1, attr2));
            }
        }

        public List<Term> getTerms() {
            return terms;
        }

        @Override
        public String toString() {
            if (str == null) {
                StringBuilder sb = new StringBuilder();
                for (var t : terms) {
                    sb.append(t.toString());
                }
                str = sb.toString();
            }
            return str;
        }
    }

    public final List<ProductStruct> products;
    private String str;

    public Product(final List<grammarParserParser.ProductsTermsContext> products) {
        this.products = new ArrayList<>();
        for (var ctx : products) {
            this.products.add(new ProductStruct(ctx));
        }
    }

    public String toString() {
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            for (var p : products) {
                sb.append(p.toString());
            }
            str = sb.toString();
        }
        return str;
    }
}
