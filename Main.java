import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "test_code.txt"; 
        SymbolTable symbolTable = new SymbolTable(); 
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            
            String line;
            System.out.println("--- Bangla Compiler: Review 1 Demo ---");
            
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                System.out.println("\n>>> Processing Line: " + line);
                
     

       
Lexer lexer = new Lexer(line);
List<Token> tokens = lexer.tokenize();


System.out.println("Tokens generated:");


for (Token token : tokens) {
    System.out.println("  -> " + token); 
}


try {
    Parser parser = new Parser(tokens, symbolTable);
    parser.parse(); 
} catch (RuntimeException e) {
    System.out.println("Error: " + e.getMessage());
}
            }
            
        
            symbolTable.printTable();
            System.out.println("\n--- Compilation Finished ---");
            
        } catch (IOException e) {
            System.out.println("Error: 'test_code.txt' file not found!");
        }
    }
}