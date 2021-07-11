// Generated from C:/Users/Nik Carlson/Desktop/MTLab4/src/GrammarParser\grammarParser.g4 by ANTLR 4.9.1
package GrammarParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class grammarParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, STRING=5, WS=6, TERMINAL_NAME=7, RULE_NAME=8, 
		ATTR=9, PARAM=10;
	public static final int
		RULE_myGrammar = 0, RULE_terminal = 1, RULE_terminalsThing = 2, RULE_leftThing = 3, 
		RULE_r = 4, RULE_products = 5, RULE_productsTerms = 6, RULE_prodTerm = 7, 
		RULE_leftTerm = 8, RULE_rightTerm = 9, RULE_ret = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"myGrammar", "terminal", "terminalsThing", "leftThing", "r", "products", 
			"productsTerms", "prodTerm", "leftTerm", "rightTerm", "ret"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "';'", "'|'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "STRING", "WS", "TERMINAL_NAME", "RULE_NAME", 
			"ATTR", "PARAM"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "grammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public grammarParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MyGrammarContext extends ParserRuleContext {
		public List<RContext> r() {
			return getRuleContexts(RContext.class);
		}
		public RContext r(int i) {
			return getRuleContext(RContext.class,i);
		}
		public List<TerminalContext> terminal() {
			return getRuleContexts(TerminalContext.class);
		}
		public TerminalContext terminal(int i) {
			return getRuleContext(TerminalContext.class,i);
		}
		public MyGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_myGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterMyGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitMyGrammar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitMyGrammar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MyGrammarContext myGrammar() throws RecognitionException {
		MyGrammarContext _localctx = new MyGrammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_myGrammar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINAL_NAME || _la==RULE_NAME) {
				{
				setState(24);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RULE_NAME:
					{
					setState(22);
					r();
					}
					break;
				case TERMINAL_NAME:
					{
					setState(23);
					terminal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TerminalContext extends ParserRuleContext {
		public TerminalNode TERMINAL_NAME() { return getToken(grammarParserParser.TERMINAL_NAME, 0); }
		public TerminalsThingContext terminalsThing() {
			return getRuleContext(TerminalsThingContext.class,0);
		}
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitTerminal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitTerminal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(TERMINAL_NAME);
			setState(30);
			match(T__0);
			setState(31);
			terminalsThing();
			setState(32);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TerminalsThingContext extends ParserRuleContext {
		public LeftThingContext leftThing() {
			return getRuleContext(LeftThingContext.class,0);
		}
		public TerminalsThingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminalsThing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterTerminalsThing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitTerminalsThing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitTerminalsThing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalsThingContext terminalsThing() throws RecognitionException {
		TerminalsThingContext _localctx = new TerminalsThingContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_terminalsThing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			leftThing();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftThingContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(grammarParserParser.STRING, 0); }
		public LeftThingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftThing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterLeftThing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitLeftThing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitLeftThing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftThingContext leftThing() throws RecognitionException {
		LeftThingContext _localctx = new LeftThingContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_leftThing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RContext extends ParserRuleContext {
		public TerminalNode RULE_NAME() { return getToken(grammarParserParser.RULE_NAME, 0); }
		public ProductsContext products() {
			return getRuleContext(ProductsContext.class,0);
		}
		public TerminalNode PARAM() { return getToken(grammarParserParser.PARAM, 0); }
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_r);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(RULE_NAME);
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARAM) {
				{
				setState(39);
				match(PARAM);
				}
			}

			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(42);
				ret();
				}
			}

			setState(45);
			match(T__0);
			setState(46);
			products();
			setState(47);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProductsContext extends ParserRuleContext {
		public List<ProductsTermsContext> productsTerms() {
			return getRuleContexts(ProductsTermsContext.class);
		}
		public ProductsTermsContext productsTerms(int i) {
			return getRuleContext(ProductsTermsContext.class,i);
		}
		public ProductsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_products; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterProducts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitProducts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitProducts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductsContext products() throws RecognitionException {
		ProductsContext _localctx = new ProductsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_products);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			productsTerms();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(50);
				match(T__2);
				setState(51);
				productsTerms();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProductsTermsContext extends ParserRuleContext {
		public List<ProdTermContext> prodTerm() {
			return getRuleContexts(ProdTermContext.class);
		}
		public ProdTermContext prodTerm(int i) {
			return getRuleContext(ProdTermContext.class,i);
		}
		public ProductsTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_productsTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterProductsTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitProductsTerms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitProductsTerms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductsTermsContext productsTerms() throws RecognitionException {
		ProductsTermsContext _localctx = new ProductsTermsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_productsTerms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			prodTerm();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TERMINAL_NAME || _la==RULE_NAME) {
				{
				{
				setState(58);
				prodTerm();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProdTermContext extends ParserRuleContext {
		public LeftTermContext leftTerm() {
			return getRuleContext(LeftTermContext.class,0);
		}
		public RightTermContext rightTerm() {
			return getRuleContext(RightTermContext.class,0);
		}
		public ProdTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prodTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterProdTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitProdTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitProdTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProdTermContext prodTerm() throws RecognitionException {
		ProdTermContext _localctx = new ProdTermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_prodTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			leftTerm();
			setState(65);
			rightTerm();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftTermContext extends ParserRuleContext {
		public TerminalNode RULE_NAME() { return getToken(grammarParserParser.RULE_NAME, 0); }
		public TerminalNode TERMINAL_NAME() { return getToken(grammarParserParser.TERMINAL_NAME, 0); }
		public LeftTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterLeftTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitLeftTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitLeftTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftTermContext leftTerm() throws RecognitionException {
		LeftTermContext _localctx = new LeftTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_leftTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !(_la==TERMINAL_NAME || _la==RULE_NAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightTermContext extends ParserRuleContext {
		public TerminalNode PARAM() { return getToken(grammarParserParser.PARAM, 0); }
		public TerminalNode ATTR() { return getToken(grammarParserParser.ATTR, 0); }
		public RightTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterRightTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitRightTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitRightTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightTermContext rightTerm() throws RecognitionException {
		RightTermContext _localctx = new RightTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rightTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARAM) {
				{
				setState(69);
				match(PARAM);
				}
			}

			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATTR) {
				{
				setState(72);
				match(ATTR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RetContext extends ParserRuleContext {
		public TerminalNode PARAM() { return getToken(grammarParserParser.PARAM, 0); }
		public RetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).enterRet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof grammarParserListener ) ((grammarParserListener)listener).exitRet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof grammarParserVisitor ) return ((grammarParserVisitor<? extends T>)visitor).visitRet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetContext ret() throws RecognitionException {
		RetContext _localctx = new RetContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ret);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__3);
			setState(76);
			match(PARAM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\fQ\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\5\6+\n\6\3\6\5\6.\n\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7"+
		"\67\n\7\f\7\16\7:\13\7\3\b\3\b\7\b>\n\b\f\b\16\bA\13\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\13\5\13I\n\13\3\13\5\13L\n\13\3\f\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f"+
		"\16\20\22\24\26\2\3\3\2\t\n\2M\2\34\3\2\2\2\4\37\3\2\2\2\6$\3\2\2\2\b"+
		"&\3\2\2\2\n(\3\2\2\2\f\63\3\2\2\2\16;\3\2\2\2\20B\3\2\2\2\22E\3\2\2\2"+
		"\24H\3\2\2\2\26M\3\2\2\2\30\33\5\n\6\2\31\33\5\4\3\2\32\30\3\2\2\2\32"+
		"\31\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\3\3\2\2\2\36"+
		"\34\3\2\2\2\37 \7\t\2\2 !\7\3\2\2!\"\5\6\4\2\"#\7\4\2\2#\5\3\2\2\2$%\5"+
		"\b\5\2%\7\3\2\2\2&\'\7\7\2\2\'\t\3\2\2\2(*\7\n\2\2)+\7\f\2\2*)\3\2\2\2"+
		"*+\3\2\2\2+-\3\2\2\2,.\5\26\f\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\60\7\3"+
		"\2\2\60\61\5\f\7\2\61\62\7\4\2\2\62\13\3\2\2\2\638\5\16\b\2\64\65\7\5"+
		"\2\2\65\67\5\16\b\2\66\64\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\r"+
		"\3\2\2\2:8\3\2\2\2;?\5\20\t\2<>\5\20\t\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2"+
		"?@\3\2\2\2@\17\3\2\2\2A?\3\2\2\2BC\5\22\n\2CD\5\24\13\2D\21\3\2\2\2EF"+
		"\t\2\2\2F\23\3\2\2\2GI\7\f\2\2HG\3\2\2\2HI\3\2\2\2IK\3\2\2\2JL\7\13\2"+
		"\2KJ\3\2\2\2KL\3\2\2\2L\25\3\2\2\2MN\7\6\2\2NO\7\f\2\2O\27\3\2\2\2\n\32"+
		"\34*-8?HK";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}