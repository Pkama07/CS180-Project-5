import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Store
 *
 * This class provides the operations that all Store objects can do
 *
 * @author Adrienne Peters, Gunwoo Kang, CS180 BLK
 *
 * @version Nov. 2022
 */
public class Store implements java.io.Serializable {
    private ArrayList<Product> products = new ArrayList<Product>(); //ArrayList of all the products available at the store
    private ArrayList<String> sales = new ArrayList<String>(); //ArrayList of all the items the store has sold. Each string
    // is in the format of "buyerUserName,productInfo"
    private String storeName; //The name of the store
    private Seller seller; //The Seller account associated with the Store object

    public Store(String storeName, Seller seller) {
        this.storeName = storeName;
        this.seller = seller;
    }
    public Store() {

    }

    public void addToSeller() {
        seller.getStores().add(this);
    }

    public String getStoreName() { //Returns a Store object's storeName
        return storeName;
    }

    public ArrayList<Product> getProducts() { //Returns a Store object's ArrayList of Products
        return products;
    }
    public ArrayList<String> getSales() { //Returns a Store object's ArrayList of sales
        return sales;
    }

    public Seller getSeller() { //Returns the Seller object associated with the Store
        return seller;
    }

    public void setProducts(ArrayList<Product> products) { //Sets a Store's products ArrayList to the parameter
        this.products = products;
    }

    public void createProduct() { //Creates a product from a Store's input
        //Asking the Store what the name of the Product is
        String name = JOptionPane.showInputDialog(null , "Enter product name" , "180-Market" ,
                JOptionPane.QUESTION_MESSAGE);
        if (  name == null ) {
            return;
        }
        //Asking the Store what the description of the Product is
        String description = JOptionPane.showInputDialog(null , "Enter description" , "180 Market" ,
                JOptionPane.QUESTION_MESSAGE);
        if ( description == null ) {
            return;
        }
        //Asking the Store what the quantity of the Product is
        int quantityAvailable = Integer.parseInt(JOptionPane.showInputDialog(null ,
                "Enter quantity available" , "180 Market" , JOptionPane.QUESTION_MESSAGE));

        //Asking the Store what the price of the Product is
        double price = Double.parseDouble(JOptionPane.showInputDialog(null , "Enter price" ,
                "180 Market" , JOptionPane.QUESTION_MESSAGE));
        //Making new product with the inputted information
        Product product = new Product(name, this, description, quantityAvailable, price);
        //Adding the product to the sellers product list
        products.add(product);
        //Adding the product to the universal product list
        //products2.add(product);
        //Notifying Store that the Product has been created
        int closed = JOptionPane.showConfirmDialog(null ,
                "The product has been added to your store with the following information" , "180 Market" ,
                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
        if ( closed == JOptionPane.CLOSED_OPTION || closed == JOptionPane.CANCEL_OPTION) {
            return;
        }
        product.toString();
        /*System.out.println("The product has been added to your store with the following information:\n"
                + product.toString());*/
    }

    public void modifyProduct() { //Modifies a product based on Store's input
        //Asking the Store what Product they want to change
        //System.out.println("Which product would you like to modify?");
        //Displaying available products in the store
        String[] productNames = new String[products.size()];
        Product[] productsArray = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productNames[i] = products.get(i).getName();
            productsArray[i] = products.get(i);
        }
        Product selectedProduct = (Product) JOptionPane.showInputDialog(null ,
                "Which product would you like to modify" , "180-Market" , JOptionPane.QUESTION_MESSAGE ,
                null , productsArray , productsArray[0]);
        //System.out.println(selectedProduct.getName());
        /*
        //Getting what product they want to mofify
        int index = Integer.parseInt(s.nextLine());
        boolean modifying = true;
        boolean satisfied = true;
        do {
            //Asking what attribute they want to change
            System.out.println("What would you like to modify\n0.Name\n1.Description\n2.Quantity\n3.Price");
            int choice = Integer.parseInt(s.nextLine());
            int i = products2.indexOf(products.get(index));
            String input = "";
            switch (choice) {
                case 0://Modifying name
                    do {
                        //Asking for new name
                        System.out.println("Current name : " + products.get(index).getName() +
                                "\nWhat would you like the new name to be?");
                        input = s.nextLine();
                        //Setting name to the new name
                        products.get(index).setName(input);
                        products2.get(i).setName(input);
                        System.out.println("This is the new name:\n" + products.get(index).getName());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new name
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 1://Modifying description
                    do {
                        //Asking for new description
                        System.out.println("Current description: " + products.get(index).getDescription() +
                                "\nWhat would you like the new description to be?");
                        input = s.nextLine();
                        //Setting description to new one
                        products.get(index).setDescription(input);
                        products2.get(i).setDescription(input);
                        System.out.println("This is the new description:\n" + products.get(index).getDescription());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new description
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 2://Modifying quantity
                    do {
                        //Asking for new quantity
                        System.out.println("Current quantity: " + products.get(index).getQuantityAvailable() +
                                "\nWhat would you like the new quantity to be?");
                        int iInput = Integer.parseInt(s.nextLine());
                        //Setting quantity to new one
                        products.get(index).setQuantityAvailable(iInput);
                        products2.get(i).setQuantityAvailable(iInput);
                        System.out.println("This is the new quantity:\n" + products.get(index).getQuantityAvailable());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new quantity
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 3://Modifying price
                    do {
                        //Asking for new price
                        System.out.printf("Current price: %.2f \nWhat would you like the new price to be?\n",
                                products.get(index).getPrice());
                        double dInput = Double.parseDouble(s.nextLine());
                        //Setting price to a new one
                        products.get(index).setPrice(dInput);
                        products2.get(i).setPrice(dInput);
                        System.out.printf("This is the new price:\n%.2f\n", products.get(index).getPrice());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new price
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                default:
                    //If the Store doesn't enter a number 1-3
                    System.out.println("Please enter a valid number");
                    break;
            }
        } while (modifying);*/
    }

    public void deleteProduct(Scanner s, ArrayList<Product> products2) { //Deletes a product from the Stores list of Products based on the Store's
        // input
        boolean deleting = true;
        do {
            //Asking Store what product they would like to delete
            System.out.println("Which product would you like to delete?");
            //Showing list of available products
            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + "." + products.get(i).getName());
            }
            int index = Integer.parseInt(s.nextLine());
            //Confirming they want to delete the product they entered
            System.out.println("Confirm that you would like to delete \"" + products.get(index).getName() + "\"\n" +
                    "Type x to confirm:");
            String input = s.nextLine();
            //If the Store confirms then the product will be deleted form the Store's product list
            if (input.equals("x")) {
                System.out.println(products.get(index).getName() + " has been deleted");
                int i = products2.indexOf(products.get(index));
                products2.remove(i);
                products.remove(index);
                deleting = false;
            } else { //If the Store does not confirm then the seller is asked if they want to delete a different Product
                System.out.println("Would you like to delete a different product?\ny/n:");
                input = s.nextLine();
                //If they select no then the loop stops, otherwise the loop goes back to the beginning
                if (!(input.equals("y"))) {
                    deleting = false;
                }
            }
        } while (deleting);
    }
    public void importProducts(Scanner s) { //Reads Product information from a CSV file and adds the Products to the
        // Store's products list
        //Asking Store for CSV file path
        System.out.println("Input CSV file path");
        String f = s.nextLine();
        //Making file
        File file = new File(f);
        try {
            //Making FileReader
            FileReader fr = new FileReader(file);
            //Making BufferedReader
            BufferedReader bfr = new BufferedReader(fr);
            String line = "";
            //Reading file
            while (true) {
                line = bfr.readLine();
                if (line == null) {
                    break;
                }
                //Since the file is a CSV then each variable that makes up a product is separated by a ","
                //CSV is in the format "name,description,quantity,price"
                //Splitting the info up by ","
                String[] info = line.split(",");
                //Making a new product
                Product product = new Product(info[0], this, info[2], Integer.parseInt(info[3]),
                        Double.parseDouble(info[4]));
                //Adding the product to the sellers product list
                this.products.add(product);
                //Showing user that the data has been read from the file and that the product has been made
                System.out.println("The product has been added to your store with the following information:\n"
                        + product.toString());
            }
            bfr.close();
        } catch (IOException e) {
            System.out.println("Enter a valid file path");
        } catch (Exception e) {
            System.out.println("Enter a CSV file that is correctly formatted");
        }
    }

    public void exportProducts(Scanner s) { //Writes Product information from the Store's products list to a CSV file
        //Asking Store for CSV file path
        System.out.println("Input CSV file path");
        String f = s.nextLine();
        //Making File
        File file = new File(f);
        try {
            //Making FileOutputStream
            FileOutputStream fos = new FileOutputStream(file);
            //Making PrintWriter
            PrintWriter pw = new PrintWriter(fos);
            //Taking each product in the product list and writing their variables into the following format:
            // "name,description,quantity,price"
            for (int i = 0; i < products.size(); i++) {
                String write = products.get(i).getName() + "," + products.get(i).getStore().getStoreName() + "," +
                        products.get(i).getDescription() + "," + products.get(i).getQuantityAvailable() + ","
                        + products.get(i).getPrice();
                //Writing to file
                pw.println(write);
            }
            pw.close();
            //Showing the Store that their products have been exported to the CSV file they inputted
            System.out.println("File has been exported to " + f);
        } catch (IOException e) {
            System.out.println("Enter a valid file path");
        }
    }
    public void showSales() { //Shows a Store the Customer information and revenue from their sales
        //Information is formatted in the following way
        //sale#. productName    name of user who bought this item   revenue from this sale
        String result = "";
        int total = 0;
        int totalSales = 0;
        //Looping through sales ArrayList that is formatted in the following way:
        //buyerUserName,productInformation
        //productInformation is formatted as: "name/description/storeName/quantity/price"
        for ( int i = 0; i < sales.size(); i++ ) {
            //Splitting info at every ","
            String[] sale = sales.get(i).split(",");
            //Getting the buyer's userName
            String userName = sale[0];
            //Splitting up the product information at every "/"
            String[] productInfo = sale[1].split("/");
            //Getting the price of the product
            double price = Double.parseDouble(productInfo[4]);
            result += ( i + 1 ) + ".\t" + userName + "\t" + price + "\n";
            //Adding to total revenue and total sales number
            total += price;
            totalSales = i + 1;
        }
        //Shows the seller their total sale and total revenue at the bottom
        System.out.println(result + "\nTotal Sales: " + totalSales + "\tTotal Revenue: " + total);
    }

    //find product with name provided, if non found, return null
    public Product findProduct(String name) {
        for (Product product: products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
