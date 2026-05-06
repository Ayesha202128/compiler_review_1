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
        if (pos >= tokens.size()) return tokens.get(tokens.size() - 1);
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
                
                if (type.equals("সংখ্যা")) {
                    handleNumericExpression(varName);
                } else if (type.equals("লেখা")) {
                    handleStringExpression(varName);
                }
            }
            
            consume(TokenType.SEMICOLON); 
        }else {
    throw new RuntimeException("Syntax Error: Unexpected token " + currentToken().getValue());
}
    }

 

    private void handleNumericExpression(String varName) {
     
        if (currentToken().getType() == TokenType.STRING_LITERAL) {
            throw new RuntimeException("Type Error: 'সংখ্যা' টাইপ ভেরিয়েবলে টেক্সট রাখা সম্ভব নয়।");
        }


        int finalResult = parseExpression(); 
        
        System.out.println("Success: '" + varName + "' calculated result: " + finalResult);
    }

  
    private int parseExpression() {
        int result = parseTerm();
        while (currentToken().getType() == TokenType.PLUS || currentToken().getType() == TokenType.MINUS) {
            TokenType op = currentToken().getType();
consume(op);
            int nextVal = parseTerm();
            
            if (op == TokenType.PLUS) result += nextVal;
            else result -= nextVal;
        }
        return result;
    }

  
    private int parseTerm() {
        int result = parseFactor(); 

        while (currentToken().getType() == TokenType.MULTIPLY || currentToken().getType() == TokenType.DIVIDE) {
            TokenType op = currentToken().getType();
consume(op);
            int nextVal = parseFactor();
            
            if (op == TokenType.MULTIPLY) result *= nextVal;
            else {
                if (nextVal == 0) throw new RuntimeException("Runtime Error: Division by zero!");
                result /= nextVal;
            }
        }
        return result;
    }

   
  private int parseFactor() {


    if (currentToken().getType() != TokenType.NUMBER) {
        throw new RuntimeException("Type Error: সংখ্যা প্রত্যাশিত।");
    }

    int val = Integer.parseInt(currentToken().getValue());
    consume(TokenType.NUMBER);

    return val;
}

    private void handleStringExpression(String varName) {
        if (currentToken().getType() == TokenType.NUMBER) {
            throw new RuntimeException("Type Error: 'লেখা' টাইপ ভেরিয়েবলে সরাসরি নম্বর রাখা সম্ভব নয়।");
        }

        if (currentToken().getType() == TokenType.STRING_LITERAL) {
            String value = currentToken().getValue();
            consume(TokenType.STRING_LITERAL);
            System.out.println("Success: '" + varName + "' assigned string: \"" + value + "\"");
        } else {
            throw new RuntimeException("Type Error: 'লেখা' টাইপে উদ্ধৃতি চিহ্নসহ (\" \") টেক্সট দিতে হবে।");
        }
    }
}