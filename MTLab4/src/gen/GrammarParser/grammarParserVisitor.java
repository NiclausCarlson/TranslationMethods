// Generated from C:/Users/Nik Carlson/Desktop/MTLab4/src/GrammarParser\grammarParser.g4 by ANTLR 4.9.1
package GrammarParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link grammarParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface grammarParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#myGrammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMyGrammar(grammarParserParser.MyGrammarContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#terminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminal(grammarParserParser.TerminalContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#terminalsThing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminalsThing(grammarParserParser.TerminalsThingContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#leftThing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftThing(grammarParserParser.LeftThingContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(grammarParserParser.RContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#products}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProducts(grammarParserParser.ProductsContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#productsTerms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProductsTerms(grammarParserParser.ProductsTermsContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#prodTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProdTerm(grammarParserParser.ProdTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#leftTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftTerm(grammarParserParser.LeftTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#rightTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightTerm(grammarParserParser.RightTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammarParserParser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(grammarParserParser.RetContext ctx);
}