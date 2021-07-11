grammar g;

NUMBER : [0-9];
LETTER : [a-zA-Z];

SPECIAL_SYMBOL : '#' | '%' | '&' | '~';
DELIMETER_SYMBOL : '(' | ')' | '[' | ']' | ',' | '|' | ';' | '.' | '\'';

GREEK_LETTER : '\\'('Alpha'|'alpha' | 'Beta' | 'beta' |
                'Gamma' | 'gamma' | 'Delta' | 'delta' | 'Epsilon' | 'epsilon' | 'varepsilon' |
                'Zeta' | 'zeta' | 'Eta' | 'eta' | 'Theta' | 'theta' | 'vartheta'|
                'Iota' | 'iota'| 'Kappa' | 'kappa' | 'varkappa'|
                'Lambda' | 'lambda'| 'Mu' | 'mu' | 'Nu' | 'nu' | 'Xi' | 'xi' |
                'Omicron' | 'omicron' | 'Pi' | 'pi' | 'varpi' |
                'Rho' | 'rho' | 'varrho' |  'Sigma' | 'sigma' | 'varsigma' |
                'Tau' | 'tau'|  'Upsilon' | 'upsilon' | 'Phi' | 'phi' |
                'varphi' | 'Chi' | 'chi' | 'Psi' | 'psi' | 'Omega' | 'omega');
MATH_SYMBOLS : ('\\'('neg' | 'land' | 'lor' | 'forall' | 'exists' | 'leq' | 'geq' | 'subset' | 'subseteq' |
                    'in' | 'emptyset' | 'cup' | 'cap' | 'setminus' | 'Rightarrow' | 'Leftarrow'))
                    | '=' | '<' | '>' | '+' | '-';

NEWLINE : '\r\n'+ | '\n'+;
WHITE_SPACE: [ \r\t]+;
ws : NEWLINE | WHITE_SPACE;

text : (LETTER+ | NUMBER+ | delimeter)+;
delimeter: ws | DELIMETER_SYMBOL+;

formula : simple_formula | newline_formula;
simple_formula : '$'formula_text'$';
newline_formula : '$$'formula_text'$$';

formula_text : (proxi | simple | delimeter)*;
proxi: upper_or_lower+;
upper_or_lower : uppers | lowers;
uppers : upper+;
lowers: lower+;
upper : '^'content;
lower : '_'content;
simple : LETTER+ | NUMBER+ | GREEK_LETTER | MATH_SYMBOLS;
content : (NUMBER | LETTER | GREEK_LETTER | MATH_SYMBOLS) | '{'(proxi | simple | delimeter | content)+'}';

list      : enumerate | itemize;
enumerate : '\\begin{enumerate}' (ws | items)* '\\end{enumerate}';
itemize   : '\\begin{itemize}' (ws | items)* '\\end{itemize}';
items     : '\\item' ws* (list | text | formula);

latex : (text | formula | list)*;
