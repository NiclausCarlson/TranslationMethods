package Exceptions;

public class LexerException extends KotlinFunParserException {
    public LexerException(String message) {
        super("Lexer exception: " + message);
    }
}
