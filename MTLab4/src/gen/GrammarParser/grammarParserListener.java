// Generated from C:/Users/Nik Carlson/Desktop/MTLab4/src/GrammarParser\grammarParser.g4 by ANTLR 4.9.1
package GrammarParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link grammarParserParser}.
 */
public interface grammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#myGrammar}.
	 * @param ctx the parse tree
	 */
	void enterMyGrammar(grammarParserParser.MyGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#myGrammar}.
	 * @param ctx the parse tree
	 */
	void exitMyGrammar(grammarParserParser.MyGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(grammarParserParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(grammarParserParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#terminalsThing}.
	 * @param ctx the parse tree
	 */
	void enterTerminalsThing(grammarParserParser.TerminalsThingContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#terminalsThing}.
	 * @param ctx the parse tree
	 */
	void exitTerminalsThing(grammarParserParser.TerminalsThingContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#leftThing}.
	 * @param ctx the parse tree
	 */
	void enterLeftThing(grammarParserParser.LeftThingContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#leftThing}.
	 * @param ctx the parse tree
	 */
	void exitLeftThing(grammarParserParser.LeftThingContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(grammarParserParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(grammarParserParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#products}.
	 * @param ctx the parse tree
	 */
	void enterProducts(grammarParserParser.ProductsContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#products}.
	 * @param ctx the parse tree
	 */
	void exitProducts(grammarParserParser.ProductsContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#productsTerms}.
	 * @param ctx the parse tree
	 */
	void enterProductsTerms(grammarParserParser.ProductsTermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#productsTerms}.
	 * @param ctx the parse tree
	 */
	void exitProductsTerms(grammarParserParser.ProductsTermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#prodTerm}.
	 * @param ctx the parse tree
	 */
	void enterProdTerm(grammarParserParser.ProdTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#prodTerm}.
	 * @param ctx the parse tree
	 */
	void exitProdTerm(grammarParserParser.ProdTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#leftTerm}.
	 * @param ctx the parse tree
	 */
	void enterLeftTerm(grammarParserParser.LeftTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#leftTerm}.
	 * @param ctx the parse tree
	 */
	void exitLeftTerm(grammarParserParser.LeftTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#rightTerm}.
	 * @param ctx the parse tree
	 */
	void enterRightTerm(grammarParserParser.RightTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#rightTerm}.
	 * @param ctx the parse tree
	 */
	void exitRightTerm(grammarParserParser.RightTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammarParserParser#ret}.
	 * @param ctx the parse tree
	 */
	void enterRet(grammarParserParser.RetContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammarParserParser#ret}.
	 * @param ctx the parse tree
	 */
	void exitRet(grammarParserParser.RetContext ctx);
}