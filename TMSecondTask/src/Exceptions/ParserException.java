package Exceptions;

public class ParserException extends KotlinFunParserException {
    public ParserException(String token, String parsedStr) {
        super("Unexpected token (" + token + ") got from string: " + parsedStr);
    }
}
