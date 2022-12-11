
/**
 * Products when placed in cart
 *
 * @author Amber Joneleit
 * @version 11/30/22
 */
public class ProductAdded extends Product {
    
    int inCart;
    
    public ProductAdded(String name, Store store, String description, int quantityAvailable, double price, int inCart) {
        super(name, store, description, quantityAvailable, price);
        this.inCart = inCart;
    }
    
    public void setInCart(int inCart) {
        this.inCart = inCart;
    }
    
    public int getInCart() {
        return inCart;
    }
    
}
