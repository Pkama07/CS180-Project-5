import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * CreateProduct.java
 *
 * This class allows for Sellers to create new products to add to their store
 * FileChooser info found at:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class CreateProduct extends JComponent implements Runnable {
    TextField name;
    TextField description;
    TextField quantity;
    TextField price;
    JButton createButton;
    public void run() {
        return;
    }
    public void run(String storeName) {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring the frame will close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Creating and formatting buttons/textFields
        name = new TextField("Name");
        content.add(name);
        description = new TextField("Description");
        content.add(description);
        quantity = new TextField("Quantity");
        content.add(quantity);
        price = new TextField("Price");
        content.add(price);
        //Making Panel to hold all buttons
        Panel buttonPanel = new Panel();
        new BoxLayout(buttonPanel , BoxLayout.X_AXIS);
        createButton = new JButton("Create");
        buttonPanel.add(createButton);
        content.add(buttonPanel);
        frame.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the create button
                //Close the frame
                frame.dispose();
                //Making a new product from the information in the text fields
                try {
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("storeName", storeName);
                    hm.put("productName", name.getText());
                    hm.put("description", description.getText());
                    hm.put("quantity", quantity.getText());
                    hm.put("price", price.getText());
                    boolean response;
                    try {
                        Client.out.writeObject(new Message("createProduct", hm));
                        response = (boolean) Client.in.readObject();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    //Changing image of JOptionPane
                    ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                    Image logoImage = logo.getImage();
                    //Resizing the image
                    Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                    logo = new ImageIcon(resizedLogo);
                    //Showing the Seller the product they just created
                    int closed = JOptionPane.showConfirmDialog(null ,
                            "The product has been added with the following information:" ,
                            "180 Market" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                    if ( closed == JOptionPane.CLOSED_OPTION || closed == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    String result = String.format("Name: %s\nStore: %s\nDescription: " + "%s\nQuantity: %d\nPrice: %.2f", name.getText(), storeName, description.getText(), Integer.valueOf(quantity.getText()), Double.valueOf(price.getText()));
                    closed = JOptionPane.showConfirmDialog(null , result , "180 Market" ,
                    JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
                    if ( closed == JOptionPane.CLOSED_OPTION ) {
                        closed = JOptionPane.CLOSED_OPTION;
                    } else if ( closed == JOptionPane.CANCEL_OPTION) {
                        closed =  JOptionPane.CANCEL_OPTION;
                    }
                    if ( closed == JOptionPane.CLOSED_OPTION || closed == JOptionPane.CANCEL_OPTION) {
                        frame.dispose();
                        return;
                    }
                    //Goes back to the StoreOptions' run method
                    StoreOptions storeOptionsMenu = new StoreOptions();
                    storeOptionsMenu.run();
                } catch ( NumberFormatException ex ) {
                    //Changing image of JOptionPane
                    ImageIcon warning = new ImageIcon("WarningImage.png");
                    Image warningImage = warning.getImage();
                    //Resizing the image
                    Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                    warning = new ImageIcon(resizedWarning);
                    int closed = JOptionPane.showConfirmDialog(null,
                            "Please enter a number in the quantity and price fields", "180-Market",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE , warning);
                    if ( closed == JOptionPane.CANCEL_OPTION ) {
                        ManageStore manageStoreMenu = new ManageStore();
                        frame.dispose();
                        manageStoreMenu.run();
                    }
                    if ( closed == JOptionPane.CLOSED_OPTION ) {
                        return;
                    }
                    if ( closed == JOptionPane.OK_OPTION ) {
                        frame.dispose();
                        run();
                    }
                }
                frame.dispose();
                StoreOptions storeOptionsMenu = new StoreOptions();
                storeOptionsMenu.run(storeName);
            }
        });
    }
}
