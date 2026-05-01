// Token.java
enum TokenType {
    DATA_TYPE,      // সংখ্যা, লেখা
    IDENTIFIER,    // ক, খ, নাম
    ASSIGN,        // =
    NUMBER,        // ১০, ২০
    STRING_LITERAL, // "আয়শা" (এটি আপনার কোডে মিসিং ছিল)
    PLUS, MINUS, MULTIPLY, DIVIDE, // +, -, *, /
    SEMICOLON,     // ;
    EOF            // এন্ড অফ ফাইল
}

public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() { return type; }
    public String getValue() { return value; }

    @Override
    public String toString() {
        return "Token(" + type + ", '" + value + "')";
    }
}