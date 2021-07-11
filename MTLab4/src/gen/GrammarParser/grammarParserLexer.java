// Generated from C:/Users/Nik Carlson/Desktop/MTLab4/src/GrammarParser\grammarParser.g4 by ANTLR 4.9.1
package GrammarParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class grammarParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, STRING=5, WS=6, TERMINAL_NAME=7, RULE_NAME=8, 
		ATTR=9, PARAM=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "STRING", "WS", "TERMINAL_NAME", "RULE_NAME", 
			"ATTR", "PARAM"
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


	public grammarParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "grammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fR\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\7\6\'"+
		"\n\6\f\6\16\6*\13\6\3\6\3\6\3\7\6\7/\n\7\r\7\16\7\60\3\7\3\7\3\b\3\b\7"+
		"\b\67\n\b\f\b\16\b:\13\b\3\t\3\t\7\t>\n\t\f\t\16\tA\13\t\3\n\3\n\6\nE"+
		"\n\n\r\n\16\nF\3\n\3\n\3\13\3\13\6\13M\n\13\r\13\16\13N\3\13\3\13\5(F"+
		"N\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\3\2\6\5\2\13\f\17"+
		"\17\"\"\3\2C\\\7\2))\62;C\\aac|\3\2c|\2W\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\31\3\2\2\2\7\33\3\2\2\2\t\35"+
		"\3\2\2\2\13$\3\2\2\2\r.\3\2\2\2\17\64\3\2\2\2\21;\3\2\2\2\23B\3\2\2\2"+
		"\25J\3\2\2\2\27\30\7<\2\2\30\4\3\2\2\2\31\32\7=\2\2\32\6\3\2\2\2\33\34"+
		"\7~\2\2\34\b\3\2\2\2\35\36\7t\2\2\36\37\7g\2\2\37 \7v\2\2 !\7w\2\2!\""+
		"\7t\2\2\"#\7p\2\2#\n\3\2\2\2$(\7)\2\2%\'\13\2\2\2&%\3\2\2\2\'*\3\2\2\2"+
		"()\3\2\2\2(&\3\2\2\2)+\3\2\2\2*(\3\2\2\2+,\7)\2\2,\f\3\2\2\2-/\t\2\2\2"+
		".-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63\b"+
		"\7\2\2\63\16\3\2\2\2\648\t\3\2\2\65\67\t\4\2\2\66\65\3\2\2\2\67:\3\2\2"+
		"\28\66\3\2\2\289\3\2\2\29\20\3\2\2\2:8\3\2\2\2;?\t\5\2\2<>\t\4\2\2=<\3"+
		"\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\22\3\2\2\2A?\3\2\2\2BD\7}\2\2CE"+
		"\13\2\2\2DC\3\2\2\2EF\3\2\2\2FG\3\2\2\2FD\3\2\2\2GH\3\2\2\2HI\7\177\2"+
		"\2I\24\3\2\2\2JL\7]\2\2KM\13\2\2\2LK\3\2\2\2MN\3\2\2\2NO\3\2\2\2NL\3\2"+
		"\2\2OP\3\2\2\2PQ\7_\2\2Q\26\3\2\2\2\t\2(\608?FN\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}