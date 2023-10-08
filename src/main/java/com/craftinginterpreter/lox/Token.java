package com.craftinginterpreter.lox;

class Token {
    final TokenType tokenType;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type,String lexeme,Object literal,int line){
        this.tokenType=type;
        this.lexeme=lexeme;
        this.literal=literal;
        this.line=line;

    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", lexeme='" + lexeme + '\'' +
                ", literal=" + literal +
                '}';
    }
}
