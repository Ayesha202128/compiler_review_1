import java.util.HashMap;

public class SymbolTable {
    // ভেরিয়েবল নাম (String) এবং তার টাইপ (String) জমা রাখার জন্য HashMap
    private HashMap<String, String> table = new HashMap<>(); 

    /**
     * নতুন ভেরিয়েবল সিম্বল টেবিলে যোগ করা
     * @param name ভেরিয়েবলের নাম (যেমন: ক)
     * @param type ভেরিয়েবলের ধরন (যেমন: সংখ্যা)
     */
    public void add(String name, String type) {
        table.put(name, type);
    }

    /**
     * ভেরিয়েবলটি আগে ডিক্লেয়ার করা হয়েছে কি না তা চেক করা
     * @param name ভেরিয়েবলের নাম
     * @return থাকলে true, না থাকলে false
     */
    public boolean exists(String name) {
        return table.containsKey(name);
    }

    /**
     * ভেরিয়েবলের টাইপ খুঁজে বের করা (Type Checking এর জন্য প্রয়োজন)
     * @param name ভেরিয়েবলের নাম
     * @return ভেরিয়েবলের টাইপ
     */
    public String getType(String name) {
        return table.get(name);
    }

    /**
     * সিম্বল টেবিলের বর্তমান অবস্থা দেখার জন্য (Debugging এর জন্য)
     */
    public void printTable() {
        System.out.println("\n--- Symbol Table Content ---");
        for (String name : table.keySet()) {
            System.out.println("Name: " + name + " | Type: " + table.get(name));
        }
        System.out.println("----------------------------");
    }
}