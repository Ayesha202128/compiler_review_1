import java.util.*;

public class Lexer {
    private String input;
    private int pos = 0;

    public Lexer(String input) { this.input = input; }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        int length = input.length();

        while (pos < length) {
            char current = input.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // ১. সংখ্যা চেনা
            if (Character.isDigit(current)) {
                StringBuilder number = new StringBuilder();
                while (pos < length && Character.isDigit(input.charAt(pos))) {
                    number.append(input.charAt(pos));
                    pos++;
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            // ২. স্ট্রিং বা 'লেখা'র ভ্যালু চেনা (" ")
            if (current == '"') {
                StringBuilder str = new StringBuilder();
                pos++; 
                while (pos < length && input.charAt(pos) != '"') {
                    str.append(input.charAt(pos));
                    pos++;
                }
                pos++; 
                tokens.add(new Token(TokenType.STRING_LITERAL, str.toString()));
                continue;
            }

            // ৩. বাংলা কি-ওয়ার্ড (সংখ্যা, লেখা) এবং ভেরিয়েবল নাম চেনা
            if (!Character.isWhitespace(current) && !Character.isDigit(current) && 
                "=+;-/*;\"".indexOf(current) == -1) {
                StringBuilder word = new StringBuilder();
                while (pos < length && !Character.isWhitespace(input.charAt(pos)) && 
                       "=+;-/*;\"".indexOf(input.charAt(pos)) == -1) {
                    word.append(input.charAt(pos));
                    pos++;
                }
                
                String result = word.toString();
                if (result.equals("সংখ্যা") || result.equals("লেখা")) {
                    tokens.add(new Token(TokenType.DATA_TYPE, result));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, result));
                }
                continue;
            }

            // ৪. অপারেটর এবং সেমিকোলন
            if (current == '=') tokens.add(new Token(TokenType.ASSIGN, "="));
            else if (current == '+') tokens.add(new Token(TokenType.PLUS, "+"));
            else if (current == '-') tokens.add(new Token(TokenType.MINUS, "-"));
            else if (current == '*') tokens.add(new Token(TokenType.MULTIPLY, "*"));
            else if (current == '/') tokens.add(new Token(TokenType.DIVIDE, "/"));
            else if (current == ';') tokens.add(new Token(TokenType.SEMICOLON, ";"));

            pos++;
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}