lexer grammar EqBoolLexer;

// Lexing rules: recognize tokens
LPAR: '(' ;
RPAR: ')' ;
RETURNS: 'returns' ;
END: 'end' ;
COMMA: ',' ;
EQUAL: '=' ;
SCOL: ';' ;
AND: '&' ;
OR: '|' ;
NOT: '!' ;
BOOL: ('true'|'false') ;
DESCR: 'descr' ;
EVAL: 'eval' ;
ID: [a-zA-Z_][a-zA-Z0-9_]* ;
WS: [ \t\r\n]+ -> skip ;
