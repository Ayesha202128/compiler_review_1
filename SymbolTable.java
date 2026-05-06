import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, String> table = new HashMap<>(); 

   
    public void add(String name, String type) {
        table.put(name, type);
    }

    public boolean exists(String name) {
        return table.containsKey(name);
    }

    
 
     
    public String getType(String name) {
        return table.get(name);
    }

    
    public void printTable() {
        System.out.println("\n--- Symbol Table Content ---");
        for (String name : table.keySet()) {
            System.out.println("Name: " + name + " | Type: " + table.get(name));
        }
        System.out.println("----------------------------");
    }
}