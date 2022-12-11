import javax.swing.*;
import java.awt.*;

/**
 * Product
 *
 * This class provides the operations that all Product objects can do
 *
 * @author Amber Joneleit, Adrienne Peters, CS180 BLK
 *
 * @version Nov. 2022
 */
public class Product implements java.io.Serializable {
    private String name; //The name of the Product
    private Store store; //The Store that the Product belongs to
    private String description; //The description of the Product
    private int quantityAvailable; //The quantity of the Product available
    private double price; //The price of the Product
    private int currQ; //current amount of product in shopping cart

    public Product(String name, Store store, String description, int quantityAvailable, double price) {
        this.name = name;
        this.store = store;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public int getCurrQ() {
        return currQ;
    }
    public String getName() { //Returns a Product's name
        return name;
    }
    public String getDescription() { //Returns a Product's description
        return description;
    }
    public Store getStore() { //Returns a Product's store
        return store;
    }
    public int getQuantityAvailable() { //Returns a Product's quantity
        return quantityAvailable;
    }
    public double getPrice() { //Returns a Product's price
        return price;
    }

    public void setCurrQ(int currQ) {
        this.currQ = currQ;
    }

    public void setName(String name) { //Sets a Product's name to the new name
        this.name = name;
    }
    public void setStore(Store store) { //Sets a Product's store to the new store
        this.store = store;
    }
    public void setDescription(String description) { //Sets a Product's description to the new description
        this.description = description;
    }
    public void setQuantityAvailable(int quantityAvailable) { //Sets a Product's quantity to the new quantity
        this.quantityAvailable = quantityAvailable;
    }
    public void setPrice(double price) { //Sets a Product's price to the new price
        this.price = price;
    }

    public int GUIToString(String storeName) {
        //Returns a Product's information as a String
        /*-------------------------
        * Name:                name
        * Store:              store
        * Description:  description
        * Quantity:        quantity
        * Price:              price
        * -------------------------
        * */
        //Padding the output myself
        //Making array of the lengths of each variable
        /*int[] lengths = { name.length(), store.getStoreName().length(), description.length(),
                String.valueOf(quantityAvailable).length(), String.valueOf(price).length() };
        //Sorting the array
        Arrays.sort(lengths);
        //MaxLength is how wide the menu is going to be
        //Adding 13 to the largest length because "description: " takes up 13 spaces
        int maxLength = lengths[4] + 13;
        //Making divider long enough to fit all information under it with no spill over
        String divider = "";
        for (int i = 0; i < maxLength; i++) {
            divider += "-";
        }
        //Padding variables to look better for the output
        String sName = name;
        for (int i = 0; i < maxLength - name.length() - 6; i++) {
            sName = " " + sName;
        }
        String storeName = store.getStoreName();
        for (int i = 0; i < maxLength - store.getStoreName().length() - 7; i++) {
            storeName = " " + storeName;
        }
        String sDescription = description;
        for (int i = 0; i < maxLength - description.length() - 13; i++) {
            sDescription = " " + sDescription;
        }
        String sQuantity = String.valueOf(quantityAvailable);
        for (int i = 0; i < maxLength - String.valueOf(quantityAvailable).length() - 10; i++) {
            sQuantity = " " + sQuantity;
        }
        String sPrice = String.format("%.2f", price);
        for (int i = 0; i < maxLength - String.format("%.2f", price).length() - 7; i++) {
            sPrice = " " + sPrice;
        }
        //Returning the padded String
        return String.format("%s\nName: %s\nStore: %s\nDescription: " +
                "%s\nQuantity: %s\nPrice: %s\n%s", divider, sName, storeName, sDescription, sQuantity, sPrice,
                divider);*/
        String result = String.format("Name: %s\nStore: %s\nDescription: " +
                        "%s\nQuantity: %d\nPrice: %.2f", this.getName() , this.getStore().getStoreName() ,
                this.getDescription() , this.getQuantityAvailable() , this.getPrice());
        int closed = JOptionPane.showConfirmDialog(null , result , "180 Market" ,
                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
        if ( closed == JOptionPane.CLOSED_OPTION ) {
            return JOptionPane.CLOSED_OPTION;
        } else if ( closed == JOptionPane.CANCEL_OPTION) {
            return JOptionPane.CANCEL_OPTION;
        }
        return closed;
    }
    
    public boolean equals(Object o) { //Returns true if the object sent is the same as the Product that called the
        // method and returns false if not
        //If o is not a Product return false
        if (o == null || !(o instanceof Product)) {
            return false;
        }
        //Casting o into a Product
        Product other = (Product) o;
        //If the name, store, and description match return true
        if (name.equals(other.getName()) && store.equals(other.getStore()) &&
                description.equals(other.getDescription())) {
            return (price == other.getPrice());
        }
        //If not return false
        return false;
    }
}
