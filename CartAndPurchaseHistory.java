import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * ViewMarketPlace.java
 *
 * This class allows the customer to pick to view their cart or their purchase history.
 *
 * @author Aniket Biswal, BLK
 *
 * @version Dec 7, 2022
 *
 */
public class CartAndPurchaseHistory extends JComponent implements Runnable {
    JButton cart;
    JButton purchaseHistory;
    JButton back;
    public void run () {
        //Creating frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring it closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Making and formatting buttons
        cart = new JButton("View Cart");
        cart.setAlignmentX(CENTER_ALIGNMENT);
        content.add(cart);
        purchaseHistory = new JButton("Export Purchase History");
        purchaseHistory.setAlignmentX(CENTER_ALIGNMENT);
        content.add(purchaseHistory);
        back = new JButton("Back");
        back.setAlignmentX(CENTER_ALIGNMENT);
        content.add(back);
        frame.setVisible(true);
        cart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("username", Client.username);
                Customer c;
                try {
                    Client.out.writeObject(new Message("getCustomer", hm));
                    c = (Customer) Client.in.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                c.viewShoppingCartDetails();
            }
        });
        purchaseHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame f = new JFrame();
                f.setTitle("180 Market");
                f.setSize(400 , 200);
                f.setLocationRelativeTo(null);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Container content = f.getContentPane();
                content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
                JFileChooser files = new JFileChooser();
                content.add(files);
                if ( files.showSaveDialog(files) == JFileChooser.APPROVE_OPTION ) {
                    File file = new File (files.getSelectedFile().getPath());
                    try {
                        //Making FileOutputStream and PrintWriter
                        FileOutputStream fos = new FileOutputStream(file);
                        PrintWriter pw = new PrintWriter(fos);
                        //Loooping through the Customer's productsBought ArrayList
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("username", Client.username);
                        Customer c;
                        try {
                            Client.out.writeObject(new Message("getCustomer", hm));
                            c = (Customer) Client.in.readObject();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            pw.close();
                            return;
                        }
                        for ( int i = 0; i < c.getProductsBought().size(); i++ ) {
                            //Printing information in the format found above
                            pw.println(c.getProductsBought().get(i).getName() + ":\t" +
                                    c.getProductsBought().get(i).getStore().getStoreName() +
                                    ",\t$" + c.getProductsBought().get(i).getPrice());
                        }
                        pw.close();
                    } catch (Exception ex) { //If the file path cannot be accessed
                        System.out.println("Cannot write to this file");
                    }
                }
                CustomerMenu customerMenu = new CustomerMenu();
                f.dispose();
                customerMenu.run();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerMenu customerMenu = new CustomerMenu();
                frame.dispose();
                customerMenu.run();
            }
        });
    }
}
