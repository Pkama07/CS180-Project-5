import java.io.*;
import java.util.*;

/**
 * Seller
 *
 * This class holds all the operations that a Seller class can do
 *
 * @author Amber Joneleit, Paradyun Kamaraju, Gunwoo Kang, Adrienne Peters, CS180 BLK
 *
 * @version Nov. 2022
 */
public class Seller extends User implements java.io.Serializable {
    private ArrayList<Store> stores; //stores|List of stores that the Seller has

    public Seller(String userName, String passWord, String email) {
        super(userName, passWord, email); //Calls the User constructor
        stores = new ArrayList<Store>();
    }

    public ArrayList<Store> getStores() { //Returns a Seller's list of Stores
        return stores;
    }

    /*
     * 
     * 
     * if isSorted is true, the most frequent customers are listed
     * first and the most popular products are listed first
     * 
     * transactions.txt:
     * seller|store|product|customer
     * 
     * @resource https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     * ^^ used this to sort the HashMap
     */
   /* public void getStatistics(boolean isSorted) { //Returns the statistics of a Seller
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String transaction = br.readLine();
            HashMap<String, Integer> customers = new HashMap<String, Integer>();
            HashMap<String, Integer> products = new HashMap<String, Integer>();
            while(transaction != null) {
                String[] info = transaction.split("\\|");
                String seller = info[0];
                if (!seller.equals(this.getUserName())) {
                    transaction = br.readLine();
                    continue;
                }
                String product = info[2];
                String customer = info[3];
                int quantity = Integer.parseInt(info[4]);
                if (customers.containsKey(customer)) {
                    customers.put(customer, customers.get(customer) + quantity);
                } else {
                    customers.put(customer, quantity);
                }
                if (products.containsKey(product)) {
                    products.put(product, products.get(product) + quantity);
                } else {
                    products.put(product, quantity);
                }
                transaction = br.readLine();
            }
            if (isSorted) {
                LinkedList<Map.Entry<String, Integer>> customerList = new LinkedList<Map.Entry<String, Integer>>(customers.entrySet());
                LinkedList<Map.Entry<String, Integer>> productList = new LinkedList<Map.Entry<String, Integer>>(products.entrySet());
                Comparator<Map.Entry<String, Integer>> comp = new Comparator<Map.Entry<String, Integer> >() {
                    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                        return (entry1.getValue()).compareTo(entry2.getValue());
                    }
                };
                Collections.sort(customerList, comp);
                Collections.sort(productList, comp);
                customers = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: customerList) {
                    customers.put(e.getKey(), e.getValue());
                }
                products = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: productList) {
                    products.put(e.getKey(), e.getValue());
                }
            }

            System.out.println("Customer Statistics");
            System.out.println("-------------------");
            for (Map.Entry<String, Integer> e: customers.entrySet()) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }
            System.out.println();
            System.out.println("Product Statistics");
            System.out.println("------------------");
            for (Map.Entry<String, Integer> e: products.entrySet()) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }


        } catch (IOException e) {
            System.out.println("There was an error reading from transactions.txt.");
        }
    } */

    public String getStats(boolean isSorted) {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String transaction = br.readLine();
            HashMap<String, Integer> customers = new HashMap<String, Integer>();
            HashMap<String, Integer> products = new HashMap<String, Integer>();
            while(transaction != null) {
                String[] info = transaction.split("\\|");
                String sellerName = info[0];
                if (!sellerName.equals(getUserName())) {
                    transaction = br.readLine();
                    continue;
                }
                String product = info[2];
                String customer = info[3];
                int quantity = Integer.parseInt(info[4]);
                if (customers.containsKey(customer)) {
                    customers.put(customer, customers.get(customer) + quantity);
                } else {
                    customers.put(customer, quantity);
                }
                if (products.containsKey(product)) {
                    products.put(product, products.get(product) + quantity);
                } else {
                    products.put(product, quantity);
                }
                transaction = br.readLine();
            }
            if (isSorted) {
                LinkedList<Map.Entry<String, Integer>> customerList = new LinkedList<Map.Entry<String, Integer>>(customers.entrySet());
                LinkedList<Map.Entry<String, Integer>> productList = new LinkedList<Map.Entry<String, Integer>>(products.entrySet());
                Comparator<Map.Entry<String, Integer>> comp = new Comparator<Map.Entry<String, Integer> >() {
                    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                        return (entry1.getValue()).compareTo(entry2.getValue());
                    }
                };
                Collections.sort(customerList, comp);
                Collections.sort(productList, comp);
                customers = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: customerList) {
                    customers.put(e.getKey(), e.getValue());
                }
                products = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: productList) {
                    products.put(e.getKey(), e.getValue());
                }
            }
            result += "Customer Statistics:";
            for (Map.Entry<String, Integer> e: customers.entrySet()) {
                result += "\n" + e.getKey() + ": " + e.getValue();
            }
            result += "\nProduct Statistics:";
            for (Map.Entry<String, Integer> e: products.entrySet()) {
                result += "\n" + e.getKey() + ": " + e.getValue();
            }
        } catch (IOException e) {
            return null;
        }
        return result;
    }
    
    public boolean equals(Object o) { //Returns true if the object is the same as the Seller that called the method or
        // false if it is not
        //If o is a Seller object
        if (o == null || !(o instanceof Seller)) {
            return false;
        }
        //Casting o into a Seller object
        Seller other = (Seller) o;
        //Return the logical test if the seller that called this method has the same username, password, and email as
        //the other object
        return (this.getUserName().equals(other.getUserName()) && this.getPassWord().equals(other.getPassWord()) &&
                this.getEmail().equals(other.getEmail()));
    }
}
