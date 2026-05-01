import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "test_code.txt"; 
        SymbolTable symbolTable = new SymbolTable(); // একটি কমন সিম্বল টেবিল
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            
            String line;
            System.out.println("--- Bangla Compiler: Review 1 Demo ---");
            
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                System.out.println("\n>>> Processing Line: " + line);
                
     

                // ২. লেক্সার দিয়ে টোকেন তৈরি
Lexer lexer = new Lexer(line);
List<Token> tokens = lexer.tokenize();

// এখানে পরিবর্তন করুন: টোকেনগুলো এক লাইনে না দেখিয়ে লুপ চালিয়ে নিচে নিচে প্রিন্ট করুন
System.out.println("Tokens generated:");
for (Token token : tokens) {
    System.out.println("  -> " + token); 
}

// ৩. পার্সার কল করা (আগের মতোই থাকবে)
try {
    Parser parser = new Parser(tokens, symbolTable);
    parser.parse(); 
} catch (RuntimeException e) {
    System.out.println("Error: " + e.getMessage());
}
            }
            
            // ৩. শেষে সিম্বল টেবিল দেখানো (রিভিউ ১ এর জন্য বোনাস পয়েন্ট)
            symbolTable.printTable();
            System.out.println("\n--- Compilation Finished ---");
            
        } catch (IOException e) {
            System.out.println("Error: 'test_code.txt' file not found!");
        }
    }
}