import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ClientHandler.java
 *
 * This class receives a socket from the Server and handles communications with the client.
 *
 * @author Pradyun Kamaraju, Gunwoo Kang, CS180BLK
 *
 * @version Dec 8, 2022
 */

public class ClientHandler extends Thread {

    Socket socket;
    public static final Object MONITOR = new Object();
    private static User user = new User();

    public ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public User findUser(String username) {
        synchronized (MONITOR) {
            for (Customer cus : MarketplaceServer.customers) {
                if (cus.getUserName().equals(username)) {
                    return cus;
                }
            }
            for (Seller s: MarketplaceServer.sellers) {
                if (s.getUserName().equals(username)) {
                    return s;
                }
            }
            return null;
        }
    }

    public Customer findCustomer(String username) {
        synchronized (MONITOR) {
            for (Customer cus : MarketplaceServer.customers) {
                if (cus.getUserName().equals(username)) {
                    return cus;
                }
            }
            return null;
        }
    }

    public Customer findCustomerByEmail(String email) {
        synchronized (MONITOR) {
            for (Customer cus : MarketplaceServer.customers) {
                if (cus.getEmail().equals(email)) {
                    return cus;
                }
            }
            return null;
        }
    }

    public Seller findSeller(String username) {
        synchronized (MONITOR) {
            for (Seller s: MarketplaceServer.sellers) {
                if (s.getUserName().equals(username)) {
                    return s;
                }
            }
            return null;
        }
    }

    public Seller findSellerByEmail(String email) {
        synchronized (MONITOR) {
            for (Seller s: MarketplaceServer.sellers) {
                if (s.getEmail() == null) {
                    continue;
                }
                if (s.getEmail().equals(email)) {
                    return s;
                }
            }
            return null;
        }
    }

    public Store findStore(String storeName) {
        synchronized(MONITOR) {
            for (Store s: MarketplaceServer.stores) {
                if (s.getStoreName().equals(storeName)) {
                    return s;
                }
            }
            return null;
        }
    }

    public static ArrayList<Product> getProducts() {
        synchronized (MONITOR) {
            ArrayList<Product> products = new ArrayList<Product>();
            for (Store s : MarketplaceServer.stores) {
                for (Product p : s.getProducts()) {
                    products.add(p);
                }
            }
            return products;
        }
    }

    @Override
    public void run() {
        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        boolean running = true;
        while (running) {
            try {
                Object o = in.readObject();
                Message msg = (Message) o;
                String func = msg.getFunc();
                System.out.println("recieved message from user of type " + func);
                HashMap<String, String> params = msg.getParams();
                for (Map.Entry<String, String> e: params.entrySet()) {
                    System.out.println(e.getKey());
                }
                switch (func) {
                    // check availability of email 
                    // email
                    case ("checkEmailAvailability"):
                        Customer cstomer = findCustomerByEmail(params.get("email"));
                        Seller sller = findSellerByEmail(params.get("email"));
                        if (cstomer != null || sller != null) {
                            out.writeObject("taken");
                            break;
                        }
                        out.writeObject("available");
                        break;
                    // check availability of username
                    // username
                    case ("checkUsernameAvailability"):
                        Customer cstmr = findCustomer(params.get("username"));
                        Seller sllr = findSellerByEmail(params.get("username"));
                        if (cstmr != null || sllr != null) {
                            out.writeObject("taken");
                            break;
                        }
                        out.writeObject("available");
                        break;
                    // sign up after username and email availability have been checked
                    // username, password, email, userType
                    case ("signUp"):
                        if (params.get("userType").equals("customer")) {
                            MarketplaceServer.customers.add(new Customer(params.get("username"), params.get("password"), params.get("email")));
                        } else {
                            MarketplaceServer.sellers.add(new Seller(params.get("username"), params.get("password"), params.get("email")));
                        }
                        out.writeObject(true);
                        break;
                    // sign in
                    // username, password
                    case ("signIn"):
                        Customer cstm = findCustomer(params.get("username"));
                        if (cstm == null) {
                            Seller sl = findSeller(params.get("username"));
                            if (sl == null || !sl.getPassWord().equals(params.get("password"))) {
                                out.writeObject("none");
                            } else {
                                out.writeObject("seller");
                            }
                        } else if (!cstm.getPassWord().equals(params.get("password"))) {
                            out.writeObject("none");
                        } else {
                            out.writeObject("customer");
                        }
                        break;
                    // view the shopping cart
                    // username
                    case ("viewShoppingCart"):
                        Customer cus = findCustomer(params.get("username"));
                        out.writeObject(cus.getShoppingCart());
                        break;
                    // search products based on name, store, or description
                    // descriptor (name, store, or description), value
                    case ("searchProducts"):
                        String descriptor = params.get("descriptor");
                        String value = params.get("value");
                        ArrayList<Product> results = new ArrayList<Product>();
                        for (Product p : getProducts()) {
                            if (descriptor.equals("name") && p.getName().contains(value)
                                    || descriptor.equals("store") && p.getStore().getStoreName().contains(value)
                                    || descriptor.equals("description") && p.getDescription().contains(value)) {
                                results.add(p);
                            }
                        }
                        out.writeObject(results.toArray());
                        break;
                    // add products to shopping cart
                    // username, sellerName, storeName, productName, quantity
                    case ("addProducts"):
                        Customer cust = findCustomer(params.get("username"));
                        for (Product p : getProducts()) {
                            if (p.getName().equals(params.get("productName"))
                                    && p.getStore().getStoreName().equals(params.get("storeName"))
                                    && p.getStore().getSeller().getUserName().equals(params.get("sellerName"))) {
                                boolean response;
                                synchronized(MONITOR) {
                                    response = cust.addToShoppingCart(p, Integer.valueOf(params.get("quantity")));
                                }
                                out.writeObject(response);
                            }
                        }
                        break;
                    // remove products from shopping cart
                    // username, sellerName, storeName, productName, quantity
                    case ("customerRemoveProducts"):
                        Customer c = findCustomer(params.get("username"));
                        for (Product p : getProducts()) {
                            if (p.getName().equals(params.get("productName"))
                                    && p.getStore().getStoreName().equals(params.get("storeName"))
                                    && p.getStore().getSeller().getUserName().equals(params.get("sellerName"))) {
                                boolean response;
                                synchronized (MONITOR) {
                                    response = c.removeFromShoppingCart(p, Integer.valueOf(params.get("quantity")));
                                }
                                out.writeObject(response);
                                break;
                            }
                        }
                        break;
                    // buy all products in the shopping cart
                    // username
                    case ("buyProducts"):
                        Customer cu = findCustomer(params.get("username"));
                        synchronized (MONITOR) {
                            // add to this customer's productsBought
                            // remove the products from the customer's shopping cart
                            // update the store of each product's sales
                            // write the change to transactions.txt
                            for (ProductAdded pa: cu.getShoppingCart()) {
                                cu.getProductsBought().add(pa);
                                // add sale
                                pa.getStore().getSales().add(cu.getUserName() + "," + pa.getName() + "/" + pa.getDescription() + "/" + pa.getStore().getStoreName() + "/" + pa.getInCart() + "/" + pa.getPrice());
                                try (PrintWriter pw = new PrintWriter(new FileOutputStream("transactions.txt", true))) {
                                    pw.println(pa.getStore().getSeller().getUserName() + "|" + pa.getStore().getStoreName() + "|"
                                            + pa.getName() + "|" + cu.getUserName() + "|" + pa.getInCart());
                                } catch (IOException e) {
                                    System.out.println("Could not write to transactions.txt");
                                }
                            }
                            cu.getShoppingCart().clear();
                        }
                        break;
                    // view purchase history
                    // username
                    case ("viewHistory"):
                        Customer custom = findCustomer(params.get("username"));
                        synchronized (MONITOR) {
                            out.writeObject(custom.getProductsBought());
                        }
                    // get statistics
                    // username
                    case ("customerGetStatistics"):
                        Customer cs = findCustomer(params.get("username"));
                        synchronized (MONITOR) {
                            out.writeObject(cs.getStatistics(params.get("isSorted").equals("true")));
                        }
                        break;
                    // create new store
                    // username, storeName
                    case ("createStore"):
                        Seller s = findSeller(params.get("username"));
                        synchronized (MONITOR) {
                            Store sto = new Store(params.get("storeName"), s);
                            sto.addToSeller();
                            MarketplaceServer.stores.add(sto);
                        }
                        out.writeObject(true);
                        break;
                    // get all of the user's stores
                    // username
                    case ("getStores"):
                        Seller sellr = findSeller(params.get("username"));
                        out.writeObject(sellr.getStores().toArray());
                        break;
                    // create new product
                    // storeName, productName, description, quantity, price
                    case ("createProduct"):
                        double price = Double.valueOf(params.get("price"));
                        int quantity = Integer.valueOf(params.get("quantity"));
                        Store st = findStore(params.get("storeName"));
                        Product p = new Product(params.get("productName"), st, params.get("description"), quantity, price);
                        synchronized (MONITOR) {
                            st.getProducts().add(p);
                        }
                        out.writeObject(true);
                        break;
                    // modify existing product
                    // storeName, productName, changed (name, description, price, quantity), newVal
                    case ("modifyProduct"):
                        Store stor = findStore(params.get("storeName"));
                        synchronized (MONITOR) {
                            for (Product pr: stor.getProducts()) {
                                if (pr.getName().equals(params.get("productName"))) {
                                    String changed = params.get("changed");
                                    if (changed.equals("name")) {
                                        String newName = params.get("newVal");
                                        pr.setName(newName);
                                    } else if (changed.equals("description")) {
                                        String newDesc = params.get("newVal");
                                        pr.setDescription(newDesc);
                                    } else if (changed.equals("price")) {
                                        double newPrice = Double.valueOf(params.get("newVal"));
                                        pr.setPrice(newPrice);
                                    } else {
                                        int newQuantity = Integer.valueOf(params.get("newVal"));
                                        pr.setQuantityAvailable(newQuantity);
                                    }
                                }
                            }
                        }
                        break;
                    // delete a product
                    // storeName, productName
                    case ("deleteProduct"):
                        Store store = findStore(params.get("storeName"));
                        synchronized (MONITOR) {
                            for (Product pr: store.getProducts()) {
                                if (pr.getName().equals(params.get("productName"))) {
                                    store.getProducts().remove(pr);
                                    break;
                                }
                            }
                        }
                        break;
                    // view sales
                    // storeName
                    case ("viewSales"):
                        Store str = findStore(params.get("storeName"));
                        synchronized(MONITOR) {
                            out.writeObject(str.getSales());
                        }
                        break;
                    // get statistics (seller)
                    // username, isSorted
                    case ("sellerGetStatistics"):
                        Seller sllrr = findSeller(params.get("username"));
                        synchronized (MONITOR) {
                            out.writeObject(sllrr.getStats(params.get("isSorted").equals("true")));
                        }
                        break;
                    // change username
                    // username, newUsername
                    case ("changeUsername"):
                        User usr = findUser(params.get("username"));
                        synchronized (MONITOR) {
                            usr.setUserName(params.get("newUsername"));
                        }
                        break;
                    // change email
                    // username, newEmail
                    case ("changeEmail"):
                        User user = findUser(params.get("username"));
                        synchronized (MONITOR) {
                            user.setEmail(params.get("newEmail"));
                        }
                        break;
                    // change password
                    // username, newPassword
                    case ("changePassword"):
                        User userr = findUser(params.get("username"));
                        synchronized (MONITOR) {
                            userr.setPassWord(params.get("newPassword"));
                        }
                        break;
                    // delete store
                    // storeName
                    case ("deleteStore"):
                        Store store3 = findStore(params.get("storeName"));
                        MarketplaceServer.stores.remove(store3);
                        break;
                    // delete user
                    // username
                    case ("deleteUser"):
                        User u = findUser(params.get("username"));
                        if (u instanceof Customer) {
                            MarketplaceServer.customers.remove((Customer) u);
                        } else {
                            MarketplaceServer.sellers.remove((Seller) u);
                        }
                        break;
                    // get products associated with a store
                    // storeName
                    case ("getProducts"):
                        ArrayList<Product> pr = new ArrayList<Product>();
                        for (Product pro: getProducts()) {
                            if (pro.getStore().getStoreName().equals(params.get("storeName"))) {
                                pr.add(pro);
                            }
                        }
                        out.writeObject(pr.toArray());
                        break;
                    // get all products
                    //
                    case ("getAllProducts"):
                        ArrayList<Product> prod = getProducts();
                        out.writeObject(prod.toArray());
                        break;
                    // get Customer
                    // username
                    case ("getCustomer"):
                        Customer cstmer = findCustomer(params.get("username"));
                        out.writeObject(cstmer);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("client thread stopped.");
                for (String type: MarketplaceServer.types) {
                    MarketplaceServer.writeData(type);
                }
                break;
            }

        }
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private synchronized Customer customerSignIn(String username, String password) {
        for (Customer customer : User.getCustomers()) {
            if (customer.getUserName().equals(username) && customer.getPassWord().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    private Seller sellerSignIn(String username, String password) {
        ArrayList<Seller> sellers = new ArrayList<>();

        synchronized (MONITOR) { //loads seller objects to sellers
            for (Store store : User.getStore()) {
                if (!sellers.contains(store.getSeller())) {
                    sellers.add(store.getSeller());
                }
            }
        }

        for (Seller seller : sellers) {
            if (seller.getUserName().equals(username) && seller.getPassWord().equals(password)) {
                return seller;
            }
        }
        return null;
    }

    private Seller sellerSignUp(String username, String email, String password, String storeName) {
        synchronized (MONITOR) {
            for (Store store : User.getStore()) {
                Seller check = store.getSeller();
                if (check.getUserName().equals(username) || check.getEmail().equals(email)
                        || check.getEmail().equals(password) || store.getStoreName().equals(storeName)) {
                    return null;
                }
            }
        }
        Seller seller = new Seller(username, password, email);
        new Store(storeName, seller);

        return seller;
    }
}
