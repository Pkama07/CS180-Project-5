import java.util.ArrayList;

/**
 * Page Listing all Items
 *
 * @author Amber
 * @version 11/12/22
 */
public class Market {
    
    private ArrayList<Product> itemsForSale; //The items that can be bought
    
    public Market() {
        //Initializing the itemsForSale ArrayList
        itemsForSale =  new ArrayList<Product>();
    }
    
    public Market(Product[] products) {
        //Initializing the itemsForSale ArrayList
        itemsForSale =  new ArrayList<Product>();
        //Looping through products
        for (int i = 0; i < products.length; i++) {
            //Adding the product to the itemsForSale ArrayList
            itemsForSale.add(products[i]);   
        }
    }
    
    public ArrayList<Product> getItemsForSale() { //Returns a Market's itemsForSale ArrayList
        return itemsForSale;
    }
    
    public void addProducts(Product[] products) { //Adds products to the itemsForSale ArrayList
        //Looping through products
        for (int i = 0; i < products.length; i++) {
            //Adding the products to the itemsForSale ArrayList
            itemsForSale.add(products[i]);   
        }   
    }
    
    public void removeProducts(Product[] products) { //Deletes products from the itemsForSale ArrayList
        //Looping though products
        for (int i = 0; i < products.length; i++) {
            //Lopping through itemsForSale
            for (int j = 0; j < itemsForSale.size(); j++) {
                //If the item in the itemsForSale ArrayList match the product from the products array
                if (itemsForSale.get(j).equals(products[i])) {
                    //Item is removed from the itemsForSale ArrayList
                    itemsForSale.remove(j);
                }
            }
        }   
    }
    
    public void showCatalog(ArrayList<Product> products) { //Shows all the Products in a Product ArrayList
        //Setting up menu
        System.out.println("        Market Catalog        ");
        //Looping through products
        for (int i = 0; i < products.size(); i++) {
            //Calling toString for each product
            System.out.println(products.get(i).toString());
        }
    }
}
