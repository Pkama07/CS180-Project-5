import java.util.*;
import java.io.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.HashMap;


/**
 * Customer
 *
 *Class that extends User that holds all the operations that Customer objects can do
 *
 * @author Aniket Biswal, Amber Joneleit, Pardyun Kamaraju, and Adrienne Peters, BLK
 *
 * @version Nov 12, 2022
 */
public class Customer extends User implements java.io.Serializable {
    private ArrayList<ProductAdded> productsBought; //A list of all the products previously bought by the user
    private ShoppingCart shoppingCart; //A list of all the products in the user's cart
    private ArrayList<Product> products; // all products

    public Customer(String userName, String passWord, String email) {
        //Calling the User constructor
        super(userName, passWord, email);
        //Instantiating ArrayLists
        this.productsBought = new ArrayList<ProductAdded>();
        this.shoppingCart = new ShoppingCart();
        //Adding the Customer to the static Customer ArrayList in User
        if ( this.findCustomer(userName , passWord) == null) {
            getCustomers().add(this);
        }

        User users = new User();
        products = new ArrayList<Product>();
        for (Store store : users.getStore()) {
            for (Product product : store.getProducts()) {
                products.add(product);
            }
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<ProductAdded> getProductsBought() { //Returns a Customer object's productsBought ArrayList
        return this.productsBought;
    }

    public ArrayList<ProductAdded> getShoppingCart() { //Returns a Customer object's shoppingCart ArrayList
        return shoppingCart.getProductsInCart();
    }

    public void viewShoppingCartDetails() { //Prints the items in a Customer object's shoppingCart name, store,
        JFrame frame = new JFrame();
        frame.setTitle("Shopping Cart");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        Container content = frame.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        // and price
        //Printing number of items
        String num = "Number of unique items: " + shoppingCart.getProductsInCart().size();
        JLabel header = new JLabel(num, JLabel.CENTER);
        header.setFont(new Font("Verdana", Font.PLAIN, 30));
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        content.add(header, c);

        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        GridBagConstraints back = new GridBagConstraints();
        back.gridwidth = 1;
        back.fill = GridBagConstraints.HORIZONTAL;
        back.gridx = GridBagConstraints.LAST_LINE_START;
        back.gridy++;
        frame.setVisible(true);
        //Looping through the Customer's shopping cart
        for (int i = 0; i < shoppingCart.getProductsInCart().size(); i++) {
            //Printing product name, store name, nad price
            ProductAdded p = shoppingCart.getProductsInCart().get(i);
            System.out.println(p.getQuantityAvailable());

            JTextArea name = new JTextArea("Product Name: " + p.getName() +
                    "\nStore Name: " + p.getStore().getStoreName() + "\nPrice: $" + p.getPrice());
            name.setFont(new Font("Verdana", Font.PLAIN, 15));
            name.setEditable(false);
            c.gridx = 0;
            c.gridy = i + 1;
            content.add(name, c);
            c.fill = GridBagConstraints.HORIZONTAL;

            JButton button = new JButton("View " + shoppingCart.getProductsInCart().get(i).getName());
            c.ipady = 30;
            c.gridx = 1;
            c.gridy = i + 1;
            content.add(button, c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame f = new JFrame();

                    f.setTitle(p.getName());
                    f.setSize(400, 300);
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                    //f.pack();
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    frame.dispose();

                    Container cont = f.getContentPane();
                    cont.setLayout(new GridBagLayout());
                    GridBagConstraints b = new GridBagConstraints();

                    b.fill = GridBagConstraints.HORIZONTAL;
                    b.weightx = 0.5;
                    b.gridwidth = 6;
                    b.gridx = 0;
                    b.gridy = 0;

                    JLabel name = new JLabel(p.getName(), SwingConstants.CENTER);
                    JLabel store = new JLabel("Store: " + p.getStore().getStoreName(), SwingConstants.CENTER);
                    JLabel des = new JLabel(p.getDescription(), SwingConstants.CENTER);
                    JLabel price = new JLabel("$" + p.getPrice(), SwingConstants.CENTER);
                    JLabel quanAvail = new JLabel("Quantity Available: " + p.getQuantityAvailable(), SwingConstants.CENTER);
                    JLabel[] labels = {store, des, price, quanAvail};

                    name.setFont(new Font("Serif", Font.BOLD, 20));

                    cont.add(name, b);
                    b.gridy++;
                    b.fill = GridBagConstraints.HORIZONTAL;

                    for (int j = 0; j < labels.length; j++) {
                        labels[j].setFont(new Font("Serif", Font.PLAIN, 20));
                        cont.add(labels[j], b);
                        b.gridy++;
                        b.fill = GridBagConstraints.HORIZONTAL;
                    }

                    JLabel quan = new JLabel("Quantity: " + p.getInCart(), JLabel.CENTER);


                    JButton increase = new JButton("↑");
                    JButton decrease = new JButton("↓ ");
                    JButton remove = new JButton("Remove from Cart");
                    JButton back = new JButton("Back to Cart");

                    b.gridwidth = 2;
                    cont.add(increase, b);
                    b.gridx = 2;
                    b.fill = GridBagConstraints.HORIZONTAL;
                    cont.add(quan, b);
                    b.gridx = 4;
                    b.fill = GridBagConstraints.HORIZONTAL;
                    cont.add(decrease, b);
                    b.fill = GridBagConstraints.HORIZONTAL;

                    b.gridy++;
                    b.gridx = 0;
                    b.gridwidth = 3;
                    b.ipady = 20;

                    cont.add(remove, b);
                    b.fill = GridBagConstraints.HORIZONTAL;
                    b.gridx = 3;
                    cont.add(back, b);
                    b.fill = GridBagConstraints.HORIZONTAL;

                    b.gridy++;
                    JButton buy = new JButton("Buy");
                    cont.add(buy , b);

                    back.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            viewShoppingCartDetails();
                            f.dispose();
                        }
                    });
                    remove.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int ans;
                            String message = "Are you sure you want to remove ";
                            if (p.getInCart() == 1) {
                                message += "1 item from cart?";
                            } else {
                                message += p.getInCart() + " items from cart?";
                            }
                            ans = JOptionPane.showConfirmDialog(null, message , p.getName(), JOptionPane.YES_NO_OPTION);
                            if (ans == 0) {
                                f.dispose();
                                for (int j = 0; j < shoppingCart.getProductsInCart().size(); j++) {
                                    System.out.println(shoppingCart.getProductsInCart().get(j));
                                }
                                HashMap<String, String> hm = new HashMap<String, String>();
                                hm.put("username", Client.username);
                                hm.put("sellerName", p.getStore().getSeller().getUserName());
                                hm.put("storeName", p.getStore().getStoreName());
                                hm.put("productName", p.getName());
                                hm.put("quantity", String.valueOf(p.getInCart()));
                                boolean response;
                                try {
                                    Client.out.writeObject(new Message("customerRemoveProducts", hm));
                                    response = (boolean) Client.in.readObject();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    return;
                                }
                                removeFromShoppingCart(p, p.getInCart());
                                viewShoppingCartDetails();
                            }

                        }
                    });
                    increase.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (p.getQuantityAvailable() == 0) {
                                String message = p.getName() + " is out of stock!";
                                JOptionPane.showMessageDialog(null, message);
                            } else {
                                HashMap<String, String> hm = new HashMap<String, String>();
                                hm.put("username", Client.username);
                                hm.put("sellerName", p.getStore().getSeller().getUserName());
                                hm.put("storeName", p.getStore().getStoreName());
                                hm.put("productName", p.getName());
                                hm.put("quantity", String.valueOf(1));
                                boolean response;
                                try {
                                    Client.out.writeObject(new Message("addProducts", hm));
                                    response = (boolean) Client.in.readObject();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    return;
                                }
                                addToShoppingCart(p, 1);
                                quan.setText("Quantity: " + p.getInCart());
                                quanAvail.setText("Quantity Available: " + p.getQuantityAvailable());
                                quan.update(f.getGraphics());
                                quanAvail.update(f.getGraphics());
                            }
                        }
                    });
                    decrease.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (p.getInCart() == 1) {
                                int ans;
                                String message = "Do you want to remove " + p.getName() + " from cart?";
                                ans = JOptionPane.showConfirmDialog(null, message, p.getName(), JOptionPane.YES_NO_OPTION);
                                if (ans == 0) {
                                    f.dispose();
                                    HashMap<String, String> hm = new HashMap<String, String>();
                                    hm.put("username", Client.username);
                                    hm.put("sellerName", p.getStore().getSeller().getUserName());
                                    hm.put("storeName", p.getStore().getStoreName());
                                    hm.put("productName", p.getName());
                                    hm.put("quantity", String.valueOf(p.getInCart()));
                                    boolean response;
                                    try {
                                        Client.out.writeObject(new Message("customerRemoveProducts", hm));
                                        response = (boolean) Client.in.readObject();
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        return;
                                    }
                                    removeFromShoppingCart(p, 1);
                                    viewShoppingCartDetails();
                                }
                            } else {
                                HashMap<String, String> hm = new HashMap<String, String>();
                                hm.put("username", Client.username);
                                hm.put("sellerName", p.getStore().getSeller().getUserName());
                                hm.put("storeName", p.getStore().getStoreName());
                                hm.put("productName", p.getName());
                                hm.put("quantity", String.valueOf(p.getInCart()));
                                boolean response;
                                try {
                                    Client.out.writeObject(new Message("customerRemoveProducts", hm));
                                    response = (boolean) Client.in.readObject();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    return;
                                }
                                removeFromShoppingCart(p, 1);
                                quan.setText("Quantity: " + p.getInCart());
                                quanAvail.setText("Quantity Available: " + p.getQuantityAvailable());
                                quan.update(f.getGraphics());
                                quanAvail.update(f.getGraphics());
                            }
                        }
                    });
                }
            });
        }
        c.gridx = 0;
        c.gridy++;
        c.ipady = 30;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton contShop = new JButton("Continue Shopping");
        content.add(contShop, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton buy = new JButton("Buy");
        content.add(buy, c);

        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                if (shoppingCart.getProductsInCart().size() < 1) {
                    String[] options = {"OK", "Continue Shopping"};
                    int ans = JOptionPane.showOptionDialog(null, "No items in cart!", "Cart", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (ans == 0) {
                        viewShoppingCartDetails();
                    } else {
                        frame.dispose();
                        //View customer menu
                        CustomerMenu customerMenu = new CustomerMenu();
                        customerMenu.run();
                    }
                } else {
                    double totalPrice = 0;
                    String productsBuy = "";
                    for (int i = 0; i < shoppingCart.getProductsInCart().size(); i++) {
                        totalPrice += shoppingCart.getProductsInCart().get(i).getPrice();
                        productsBuy += shoppingCart.getProductsInCart().get(i).getName() + ": $" + String.format("%.2f", shoppingCart.getProductsInCart().get(i).getPrice()) + "\n";
                    }
                    String[] options = {"Back to Cart", "Buy", "Continue Shopping"};
                    String price = String.format("Total Price: $%.2f", totalPrice);
                    int ans = JOptionPane.showOptionDialog(null, "Confirm Purchase: \n" + productsBuy + price, "Buy", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (ans == 0) {
                        viewShoppingCartDetails();
                    } else if (ans == 1) {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("username", Client.username);
                        try {
                            Client.out.writeObject(new Message("buyProducts", hm));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        shoppingCart.productsInCart.clear();
                        frame.dispose();
                        CustomerMenu customerMenu = new CustomerMenu();
                        customerMenu.run();
                    } else {
                        frame.dispose();
                        //View customer menu
                        CustomerMenu customerMenu = new CustomerMenu();
                        customerMenu.run();
                    }

                }
            }
        });
        contShop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //View customer menu
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.run();

            }
        });
    }

    public void viewProducts(ArrayList<Product> products) { //Prints the information of the Products in products
        //Looping through products
        for (int i = 0; i < products.size(); i++) {
            //Printing products information
            System.out.println(products.get(i).toString() + "\n");
        }
    }

    public Product searchProductsName(ArrayList<Product> products, Scanner s) { //Searches through the products
        // ArrayList to find the Product that matches the Customer's search criteria (name)
        //Asking Customer what product name they are looking for
        System.out.println("What product would you like to search for?");
        String productName = s.nextLine();
        String result = "";
        //Looping through products
        for (int i = 0; i < products.size(); i++) {
            //If product name contains the product name the Customer inputted
            if (products.get(i).getName().contains(productName)) {
                //Adding the product's information to the result String
                result += i + ".\n" + products.get(i).toString() + "\n";
            }
        }
        //Asking user what product they would like to select and showing the result of their search
        //If nothing has been added to result String
        if ( result.equals("") ) {
            //Nothing matches the Customer's search
            System.out.println("No products match your search");
            return null;
        } else {
            System.out.println("What product would you like to select?");
            System.out.println(result);
            //Getting user input
            int index = Integer.parseInt(s.nextLine());
            //Returning the Product at that index
            return products.get(index);
        }
    }

    public Product searchProductsStore(ArrayList<Product> products, Scanner s) { //Searches through the products
        // ArrayList to find the Product that matches the Customer's search criteria (store name)
        //Asking Customer what store they are looking for
        System.out.println("What store would you like to search from?");
        String productStoreName = s.nextLine();
        String result = "";
        //Looping through products
        for (int i = 0; i < products.size(); i++) {
            //If the store name contains the store name the Customer inputted
            if (products.get(i).getStore().getStoreName().contains(productStoreName)) {
                //Adding the product's information to the result String
                result += i + ".\n" + products.get(i).toString() + "\n";
            }
        }
        //Asking user what product they would like to select and showing the result of their search
        //If nothing has been added to result String
        if ( result.equals("") ) {
            //Nothing matches the Customer's search
            System.out.println("No products match your search");
            return null;
        } else {
            System.out.println("What product would you like to select?");
            System.out.println(result);
            //Getting user input
            int index = Integer.parseInt(s.nextLine());
            //Returning the Product at that index
            return products.get(index);
        }
    }

    public Product searchProductsDescription(ArrayList<Product> products, Scanner s) { //Searches through the products
        // ArrayList to find the Product that matches the Customer's search criteria (description)
        //Asking Customer what description they are looking for
        System.out.println("What description would you like to search for?");
        String productDescription = s.nextLine();
        String result = "";
        //Looping through products
        for (int i = 0; i < products.size(); i++) {
            //If the description contains the description the Customer inputted
            if (products.get(i).getDescription().contains(productDescription)) {
                //Adding to result String
                result += i + ".\n"  + products.get(i).toString() + "\n";
            }
        }
        //Asking user what product they would like to select and showing the result of their search
        //If nothing has been added to result String
        if ( result.equals("") ) {
            //Nothing matches the Customer's search
            System.out.println("No products match your search");
            return null;
        } else {
            System.out.println("What product would you like to select?");
            System.out.println(result);
            //Getting user input
            int index = Integer.parseInt(s.nextLine());
            //Returning the Product at that index
            return products.get(index);
        }
    }

    public void sortPrice(ArrayList<Product> products, Scanner s) { //Sorts products ArrayList form low to high or
        // from high to low price wise
        //Asking user how they would like to sort the price
        System.out.println("Do you want to sort the price from:\n1.Low to High\n2.High to Low");
        int choice = s.nextInt();
        s.nextLine();
        //Making array easier to sort
        Product[] productsArr = new Product[products.size()];
        //Populating productsArr
        for (int i = 0; i < products.size(); i++) {
            productsArr[i] = products.get(i);
        }
        //If the Customer wants to sort from low to high
        if (choice == 1) {
            //Sorting
            for (int i = 0; i < productsArr.length; i++) {
                for (int j = 1; j < productsArr.length; j++) {
                    if (productsArr[j].getPrice() < productsArr[i].getPrice()) {
                        Product temp = productsArr[i];
                        productsArr[i] = productsArr[j];
                        productsArr[j] = temp;
                    }
                }
            }
            //If the Customer wants to sort from high to low
        } else if (choice == 2) {
            for (int i = 0; i < productsArr.length; i++) {
                for (int j = 1; j < productsArr.length; j++) {
                    if (productsArr[j].getPrice() > productsArr[i].getPrice()) {
                        Product temp = productsArr[i];
                        productsArr[i] = productsArr[j];
                        productsArr[j] = temp;
                    }
                }
            }
        }
        //Printing Product's information
        for (int i = 0; i < productsArr.length; i++) {
            System.out.println(productsArr[i].toString());
        }
    }

    public void sortQuantity(ArrayList<Product> products, Scanner s) { //Sorts products ArrayList from low to high or
        // from high to low quantity wise
        System.out.println("Do you want to sort the quantity from:\n1.Low to High\n2.High to Low");
        int choice = s.nextInt();
        s.nextLine();
        // Making array easier to sort
        Product[] productsArr = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productsArr[i] = products.get(i);
        }
        //If Customer wants to sort from low to high
        if (choice == 1) {
            //Sorting
            for (int i = 0; i < productsArr.length; i++) {
                for (int j = 1; j < productsArr.length; j++) {
                    if (productsArr[j].getQuantityAvailable() < productsArr[i].getQuantityAvailable()) {
                        Product temp = productsArr[i];
                        productsArr[i] = productsArr[j];
                        productsArr[j] = temp;
                    }
                }
            }
            //If Customer wants to sort from high to low
        } else if (choice == 2) {
            //Sorting
            for (int i = 0; i < productsArr.length; i++) {
                for (int j = 1; j < productsArr.length; j++) {
                    if (productsArr[j].getQuantityAvailable() > productsArr[i].getQuantityAvailable()) {
                        Product temp = productsArr[i];
                        productsArr[i] = productsArr[j];
                        productsArr[j] = temp;
                    }
                }
            }
        }
        //Printing Product's information
        for (int i = 0; i < productsArr.length; i++) {
            System.out.println(productsArr[i].toString());
        }
    }

    public boolean addToShoppingCart(Product product, int quantity) { //Adds product of a
        if (quantity > product.getQuantityAvailable()) {
            return false;
        }
        System.out.println("asked quantity: " + quantity);
        System.out.println(product.getQuantityAvailable());
        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
        System.out.println(product.getQuantityAvailable());
        shoppingCart.addToCart(product, quantity);
        return true;
    }

    public boolean removeFromShoppingCart(Product product, int quantity) { //Removes the given
        if (shoppingCart.removeFromCart(product, quantity)) {
            product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
            return true;
        }
        return false;
    }

    public void buyProducts() { //Buys the products from the Customer's shoppingCart
        //Looping through Customer's shoppingCart
        ArrayList<ProductAdded> cart = shoppingCart.getProductsInCart();
        for (int i = 0; i < cart.size(); i++) {
            //Adding the Product to the Customer's productsBought ArrayList
            this.productsBought.add(cart.get(i));
            //Making Product object out of the product
            Product product = cart.get(i);
            //Getting the Product's information
            String productInfo = product.getName() + "/" + product.getStore() + "/" + product.getDescription() + "/"
                    + product.getQuantityAvailable() + "/" + product.getPrice();
            //Adding the sale to the Store the Product was from sales ArrayList
            cart.get(i).getStore().getSales().add(this.getUserName()+","+productInfo);
            // add this to transactions
            try (PrintWriter pw = new PrintWriter(new FileOutputStream("transactions.txt", true))) {
                pw.println(product.getStore().getSeller().getUserName() + "|" + product.getStore().getStoreName() + "|"
                        + product.getName() + "|" + this.getUserName() + "|" + cart.get(i).getCurrQ());
            } catch (IOException e) {
                System.out.println("Could not write to transactions.txt");
            }
        cart.clear();
            //loop through cart and then remove all
        }
        //Making image of the JOptionPane to be the market's logo
        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
        Image logoImage = logo.getImage();
        //Resizing the image
        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        int closed = JOptionPane.showConfirmDialog(null ,
                "All the products in your cart have been bought!" , "180-Market" ,
                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
        if ( closed == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        if ( closed == JOptionPane.CANCEL_OPTION || closed == JOptionPane.OK_OPTION) {
            CustomerMenu customerMenu = new CustomerMenu();
            customerMenu.run();
        }
        cart.clear();
    }

    public void exportPurchaseHistory(Scanner s) { //Exports a receipt of all the Products the Customer has bought
        //Format of the receipt:
        // product name: store name, $price
        //Asking for file path
        System.out.println("Where would you like to save your purchase history?");
        String fileName = s.nextLine();
        //Making File
        File file = new File (fileName);
        //writing file history to file
        try {
            //Making FileOutputStream and PrintWriter
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            //Loooping through the Customer's productsBought ArrayList
            for ( int i = 0; i < productsBought.size(); i++ ) {
                //Printing information in the format found above
                pw.println(productsBought.get(i).getName() + ":\t" + productsBought.get(i).getStore().getStoreName() +
                        ",\t$" + productsBought.get(i).getPrice());
            }
            pw.close();
        } catch (Exception e) { //If the file path cannot be accessed
            System.out.println("Cannot write to this file");
        }
    }
    /*
     * @resource https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     * ^^ used this to sort the HashMap
     */
    public String getStatistics(boolean isSorted) { //Shows the statistics of a Customer's transactions
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String transaction = br.readLine();
            HashMap<String, Integer> storesSold = new HashMap<String, Integer>();
            HashMap<String, Integer> storesPurchased = new HashMap<String, Integer>();
            while (transaction != null) {
                String[] info = transaction.split("\\|");
                String store = info[1];
                String customer = info[3];
                int quantity = Integer.parseInt(info[4]);
                if (storesSold.containsKey(store)) {
                    storesSold.put(store, storesSold.get(store) + quantity);
                } else {
                    storesSold.put(store, quantity);
                }
                if (customer.equals(this.getUserName())) {
                    if (storesPurchased.containsKey(store)) {
                        storesPurchased.put(store, storesPurchased.get(store) + quantity);
                    } else {
                        storesPurchased.put(store, quantity);
                    }
                }
                transaction = br.readLine();
            }
            if (isSorted) {
                LinkedList<Map.Entry<String, Integer>> soldList = new LinkedList<Map.Entry<String, Integer>>(storesSold.entrySet());
                LinkedList<Map.Entry<String, Integer>> purchasedList = new LinkedList<Map.Entry<String, Integer>>(storesPurchased.entrySet());
                Comparator<Map.Entry<String, Integer>> comp = new Comparator<Map.Entry<String, Integer> >() {
                    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                        return (entry1.getValue()).compareTo(entry2.getValue());
                    }
                };
                Collections.sort(soldList, comp);
                Collections.sort(purchasedList, comp);
                storesSold = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: soldList) {
                    storesSold.put(e.getKey(), e.getValue());
                }
                storesPurchased = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> e: purchasedList) {
                    storesPurchased.put(e.getKey(), e.getValue());
                }
            }
            result += "\nStore Quantities";
            result += "\n----------------\n";
            for (Map.Entry<String, Integer> e: storesSold.entrySet()) {
                result += e.getKey() + ": " + e.getValue();
            }
            result += "\n";
            result += "\nPurchased Quantities";
            result += "\n--------------------\n";
            for (Map.Entry<String, Integer> e: storesPurchased.entrySet()) {
                result += e.getKey() + ": " + e.getValue();
            }
        } catch (IOException e) {
            System.out.println("There was an error reading from transactions.txt.");
        }
        return result;
    }
}