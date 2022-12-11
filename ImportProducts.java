import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * ImportProduct.java
 *
 * Allows the seller to import products to their store in the form of a CSV file
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class ImportProducts extends JComponent implements Runnable {
    public void run() {
        return;
    }
    public void run(String storeName) {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        //Ensuring the frame will close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        //Prompting the Seller to pick which file they want to use
        JFileChooser files = new JFileChooser();
        content.add(files);
        boolean firstProduct = true;
        int input = 0;
        int closed = 0;
         if ( files.showOpenDialog(files)  == JFileChooser.APPROVE_OPTION ) {
             File file = files.getSelectedFile();
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
                    //info[0] = name, info[2] = description, info[3] = quantity, info[4] = price
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("storeName", storeName);
                    hm.put("productName", info[0]);
                    hm.put("description", info[2]);
                    hm.put("quantity", info[3]);
                    hm.put("price", info[4]);
                    boolean response;
                    try {
                        Client.out.writeObject(new Message("createProduct", hm));
                        response = (boolean) Client.in.readObject();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    //Showing user that the data has been read from the file and that the product has been made
                    if (firstProduct) {
                        //Making image of the JOptionPane to be the market's logo
                        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                        Image logoImage = logo.getImage();
                        //Resizing the image
                        Image resizedLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        logo = new ImageIcon(resizedLogo);
                        closed = JOptionPane.showConfirmDialog(null,
                                "The following product(s) were added to your store:", "180-Market",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, logo);
                        if (closed == JOptionPane.CLOSED_OPTION) {
                            frame.dispose();
                            return;
                        }
                        if (closed == JOptionPane.CANCEL_OPTION) {
                            frame.dispose();
                            run(storeName);
                        }
                        firstProduct = false;
                    }
                    if (closed == JOptionPane.OK_OPTION) {
                        String result = String.format("Name: %s\nStore: %s\nDescription: " +
                                "%s\nQuantity: %d\nPrice: %.2f", info[0] , storeName ,
                        info[2] , Integer.valueOf(info[3]) , Double.valueOf(info[4]));
                        input = JOptionPane.showConfirmDialog(null , result , "180 Market" ,
                        JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
                        if ( input == JOptionPane.CLOSED_OPTION ) {
                            input = JOptionPane.CLOSED_OPTION;
                        } else if ( input == JOptionPane.CANCEL_OPTION) {
                            input = JOptionPane.CANCEL_OPTION;
                        }
                        if (input == JOptionPane.CLOSED_OPTION) {
                            frame.dispose();
                            return;
                        }
                        if (input == JOptionPane.CANCEL_OPTION) {
                            frame.dispose();
                            break;
                        }
                    }
                 }
                 if ( input == JOptionPane.OK_OPTION || input == JOptionPane.CANCEL_OPTION) {
                     //If user clicks cancel or ok close frame and go back previous menu
                     frame.dispose();
                     StoreOptions storeOptionsMenu = new StoreOptions();
                     storeOptionsMenu.run(storeName);
                 }
                 bfr.close();
             } catch (Exception e) {
                 //If there is an exception close the file chooser and alert the user to enter a CSV that is correctly
                 //formatted
                 e.printStackTrace();
                 frame.dispose();
                 //Changing image of JOptionPane
                 ImageIcon warning = new ImageIcon("WarningImage.png");
                 Image warningImage = warning.getImage();
                 //Resizing the image
                 Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                 warning = new ImageIcon(resizedWarning);
                 input = JOptionPane.showConfirmDialog(null ,
                         "Enter a CSV file that is correctly formatted" , "180-Market" ,
                         JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE , warning);
                 //If user closes or hits the cancel button then close the frame and return
                 if (input == JOptionPane.OK_OPTION) {
                     //If user clicks ok close the frame and run the method again
                     frame.dispose();
                     run(storeName);
                 }
                 if (input == JOptionPane.CLOSED_OPTION) {
                     //If the user closed the frame close the frame and return
                     frame.dispose();
                     return;
                 }
                 if (input == JOptionPane.CANCEL_OPTION) {
                     //If the user clicks cancel then close the frame
                     frame.dispose();
                 }
             }
         } else {
             //If the user clicks the cancel button on the file chooser go back to the previous menu
             frame.dispose();
             StoreOptions storeOptionsMenu = new StoreOptions();
             storeOptionsMenu.run(storeName);
         }
    }
}
