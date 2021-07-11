package Parser;

import Enums.Token;
import Exceptions.LexerException;

import java.io.IOException;
import java.io.InputStream;

public class Lexer {
    private final InputStream inputStream;
    private int curChar;
    private String value;
    private Token curToken;

    public Lexer(InputStream inputStream) {
        this.inputStream = inputStream;
        this.curToken = Token.BEGIN;
        nextChar();
    }

    private void nextChar() {
        try {
            curChar = inputStream.read();
        } catch (IOException ex) {
            System.err.println("Can't read next char");
        }
    }

    public void setNextToken() throws LexerException {
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }
        StringBuilder sb = new StringBuilder();
        if (Character.isLetterOrDigit(curChar) || curChar == '_') {
            while (Character.isLetterOrDigit(curChar) || curChar == '_') {
                sb.append((char) curChar);
                nextChar();
            }
            value = sb.toString();
            if (value.equals("fun")) {
                curToken = Token.FUN;
            } else {
                if (Character.isDigit(value.charAt(0))) {
                    throw new LexerException("variables or type name can't start from digit.");
                }
                if (curToken == Token.FUN || curToken == Token.COMMA || curToken == Token.LP) curToken = Token.NAME;
                else if (curToken == Token.COLON || curToken == Token.LT) {
                    if (!Character.isLetter(value.charAt(0))
                            || !Character.isUpperCase(value.charAt(0))) {
                        throw new LexerException("all types should starts with upper case letter.");
                    }
                    curToken = Token.TYPE;
                } else throw new LexerException("unexpected lexeme (" + value + ")");
            }
        } else {
            if (curChar == -1) {
                curToken = Token.END;
                return;
            }
            value = Character.toString(curChar);
            switch (curChar) {
                case ':':
                    nextChar();
                    curToken = Token.COLON;
                    break;
                case '(':
                    nextChar();
                    curToken = Token.LP;
                    break;
                case ')':
                    nextChar();
                    curToken = Token.RP;
                    break;
                case ',':
                    nextChar();
                    curToken = Token.COMMA;
                    break;
                case '<':
                    nextChar();
                    curToken = Token.LT;
                    break;
                case '>':
                    nextChar();
                    curToken = Token.RT;
                    break;
                default:
                    throw new LexerException("unknown lexeme (" + (char) curChar + ").");
            }
        }
    }

    public String getCurStr() {
        return value;
    }

    public Token getCurToken() {
        return curToken;
    }
}