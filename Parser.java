import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int pos = 0;
    private SymbolTable symbolTable;

    public Parser(List<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    private Token currentToken() {
        return tokens.get(pos);
    }

    private void consume(TokenType type) {
        if (currentToken().getType() == type) {
            pos++;
        } else {
            throw new RuntimeException("Syntax Error: Expected " + type + " but found " + currentToken().getType());
        }
    }

    public void parse() {
        while (currentToken().getType() != TokenType.EOF) {
            parseStatement();
        }
    }



private void parseStatement() {
    if (currentToken().getType() == TokenType.DATA_TYPE) {
        String type = currentToken().getValue(); 
        consume(TokenType.DATA_TYPE);

        String varName = currentToken().getValue(); 
        consume(TokenType.IDENTIFIER);

        if (symbolTable.exists(varName)) {
            throw new RuntimeException("Semantic Error: Variable '" + varName + "' already declared.");
        }
        symbolTable.add(varName, type);

        if (currentToken().getType() == TokenType.ASSIGN) {
            consume(TokenType.ASSIGN);
            
            // টাইপ অনুযায়ী ভ্যালু প্রসেস করা
            if (type.equals("সংখ্যা")) {
                // সংখ্যা বা গাণিতিক হিসাব থাকলে সেটি আগে শেষ করবে
                handleNumericExpression(varName);
            } else if (type.equals("লেখা")) {
                // লেখার মান (String) থাকলে সেটি আগে গ্রহণ করবে
                if (currentToken().getType() == TokenType.STRING_LITERAL) {
                    consume(TokenType.STRING_LITERAL); // এটিই আপনার এরর ফিক্স করবে
                    System.out.println("Success: String variable '" + varName + "' assigned.");
                } else {
                    throw new RuntimeException("Type Error: 'লেখা' টাইপ ভেরিয়েবলে টেক্সট প্রয়োজন।");
                }
            }
        }
        
        // সব ভ্যালু গ্রহণ করার পর এখন সে সেমিকোলন খুঁজবে
        consume(TokenType.SEMICOLON); 
    } else {
        pos++;
    }
}
}