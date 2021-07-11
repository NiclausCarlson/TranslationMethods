package Parser;

import Enums.Rule;
import Enums.Token;
import Exceptions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    private Tree parseName() throws KotlinFunParserException {
        if (lexer.getCurToken() == Token.NAME) {
            String name = lexer.getCurStr();
            lexer.setNextToken();
            return new Tree(Rule.NAME, name);
        }
        throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
    }

    private Tree parseSimpleType(Rule what) throws KotlinFunParserException {
        if (lexer.getCurToken() != Token.TYPE) {
            throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
        }
        return new Tree(what, lexer.getCurStr());
    }

    private Tree parseType() throws KotlinFunParserException {
        lexer.setNextToken();
        Tree t1 = parseSimpleType(Rule.SIMPLE_TYPE);
        lexer.setNextToken();
        if (lexer.getCurToken() == Token.LT) lexer.setNextToken();
        else return new Tree(Rule.TYPE, t1, new Tree(Rule.EPS));
        Tree t2 = parseSimpleType(Rule.GENERIC);
        lexer.setNextToken();
        return new Tree(Rule.TYPE, t1, t2);
    }

    private Tree parseArgument() throws KotlinFunParserException {
        Tree name = parseName();
        Tree type = parseType();
        return new Tree(Rule.ARGUMENT, name, type);
    }

    private Tree parseReturnType() throws KotlinFunParserException {
        if (lexer.getCurToken() == Token.RP) {
            lexer.setNextToken();
            if (lexer.getCurToken() == Token.END) {
                return new Tree(Rule.EPS);
            } else if (lexer.getCurToken() == Token.COLON) {
                return new Tree(Rule.RETURN_TYPE, parseType());
            }
        }
        throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
    }

    private Tree parseArguments3() throws KotlinFunParserException {
        List<Tree> args = new ArrayList<>();
        while (lexer.getCurToken() != Token.RP) {
            if (lexer.getCurToken() == Token.RT) lexer.setNextToken();
            if (lexer.getCurToken() == Token.RP) break;
            if (lexer.getCurToken() == Token.COMMA) {
                lexer.setNextToken();
                args.add(parseArgument());
            } else {
                throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
            }
        }
        return new Tree(Rule.ARGUMENTS_3, args);
    }

    private Tree parseArguments2() throws KotlinFunParserException {
        lexer.setNextToken();
        if (lexer.getCurToken() == Token.RP) {
            return new Tree(Rule.EPS);
        }
        return new Tree(Rule.ARGUMENTS_2, parseArgument(), parseArguments3());
    }

    private Tree parseArguments1() throws KotlinFunParserException {
        if (lexer.getCurToken() == Token.LP) {
            return new Tree(Rule.ARGUMENTS_1, parseArguments2());
        }
        throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
    }

    private Tree parseBody() throws KotlinFunParserException {
        lexer.setNextToken();
        return new Tree(Rule.BODY, parseName(), parseArguments1(), parseReturnType());
    }

    private Tree parseFunction() throws KotlinFunParserException {
        if (lexer.getCurToken() == Token.FUN) {
            return new Tree(Rule.FUNCTION, parseBody());
        }
        throw new ParserException(lexer.getCurToken().name(), lexer.getCurStr());
    }

    public Tree parse(InputStream inputStream) throws KotlinFunParserException {
        lexer = new Lexer(inputStream);
        lexer.setNextToken();
        return parseFunction();
    }
}
