import java.util.ArrayList;

/**
 * Stores items in cart for each costumer
 *
 * @author Amber Joneleit
 * @version 12/6/22
 */
public class ShoppingCart implements java.io.Serializable {
    
    public ArrayList<ProductAdded> productsInCart;
    
    public ShoppingCart() {
        productsInCart = new ArrayList<ProductAdded>();
    }
    
    public ArrayList<ProductAdded> getProductsInCart() {
        return productsInCart;
    }
    
    public void setProductsInCart(ArrayList<ProductAdded> productsInCart) {
        this.productsInCart = productsInCart;    
    }
    
    public boolean isInCart(Product product) {
        for (int i = 0; i < productsInCart.size(); i++) {
            if (product.equals(productsInCart.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public void addToCart(Product product, int quantity) {
        ProductAdded p = new ProductAdded(product.getName(), product.getStore(), product.getDescription(), product.getQuantityAvailable(), product.getPrice(), quantity);
        if (productsInCart == null) {
            productsInCart = new ArrayList<ProductAdded>();
            productsInCart.add(p);
            return;
        }
        for (int i = 0; i < productsInCart.size(); i++) {
            if (product.equals(productsInCart.get(i))) {
                productsInCart.get(i).setInCart(productsInCart.get(i).getInCart() + quantity);
            }
        }
        if (!isInCart(product)) {
            productsInCart.add(p);    
        }
    }
    
    public boolean removeFromCart(Product product, int quantity) {
        for (ProductAdded pa: productsInCart) {
            if (pa.getName().equals(product.getName())) {
                if (pa.getInCart() < quantity) {
                    return false;
                } else {
                    pa.setInCart(pa.getInCart() - quantity);
                    if (pa.getInCart() == 0) {
                        productsInCart.remove(pa);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
