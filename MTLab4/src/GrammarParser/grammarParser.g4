grammar grammarParser;

STRING: '\''.*?'\'';
WS: [ \t\n\r]+ -> skip;
TERMINAL_NAME: [A-Z][A-Za-z0-9_']*;
RULE_NAME: [a-z][A-Za-z0-9_']*;

myGrammar: (r | terminal)*;

terminal: TERMINAL_NAME ':' terminalsThing';' ;
terminalsThing: leftThing;
leftThing: STRING;

r: RULE_NAME PARAM? ret? ':' products ';';
products: productsTerms ('|' productsTerms)*;
productsTerms: prodTerm (prodTerm)*;
prodTerm: leftTerm rightTerm;
leftTerm: RULE_NAME | TERMINAL_NAME;
rightTerm: PARAM? ATTR?;

ATTR: '{'.+?'}';
PARAM: '['.+?']';
ret: 'return' PARAM;
