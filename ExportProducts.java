import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * ExportProduct.java
 *
 * Allows the seller to export products from their store to a CSV file
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class ExportProducts extends JComponent implements Runnable {
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
        //Having user pick where to save
        JFileChooser files = new JFileChooser();
        content.add(files);
        int input = 0;
        int closed = 0;
        if ( files.showSaveDialog(files) == JFileChooser.APPROVE_OPTION ) {
            File file = files.getSelectedFile();
                try {
                    //Making FileOutputStream
                    FileOutputStream fos = new FileOutputStream(file);
                    //Making PrintWriter
                    PrintWriter pw = new PrintWriter(fos);
                    //Taking each product in the product list and writing their variables into the following format:
                    // "name,description,quantity,price"
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("storeName", storeName);
                    Object[] products;
                    try {
                        Client.out.writeObject(new Message("getProducts", hm));
                        products = (Object[]) Client.in.readObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    for (int i = 0; i < products.length; i++) {
                        if (i == 0) {
                            //Making image of the JOptionPane to be the market's logo
                            ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                            Image logoImage = logo.getImage();
                            //Resizing the image
                            Image resizedLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            logo = new ImageIcon(resizedLogo);
                            closed = JOptionPane.showConfirmDialog(null,
                                    "The following products were exported to " + file.getPath(), "180-Market"
                                    , JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, logo);
                            if (closed == JOptionPane.CLOSED_OPTION) {
                                frame.dispose();
                                return;
                            }
                            if (closed == JOptionPane.CANCEL_OPTION) {
                                frame.dispose();
                                run(storeName);
                            }
                        }
                        if (closed == JOptionPane.OK_OPTION) {
                            //Formatting info into CSV
                            String write = ((Product)products[i]).getName() + "," +
                                    storeName + "," +
                                    ((Product)products[i]).getDescription() + "," +
                                    ((Product)products[i]).getQuantityAvailable() + ","
                                    + ((Product)products[i]).getPrice();
                            String result = String.format("Name: %s\nStore: %s\nDescription: " +
                                    "%s\nQuantity: %d\nPrice: %.2f", ((Product)products[i]).getName() , storeName ,
                                    ((Product)products[i]).getDescription() , ((Product)products[i]).getQuantityAvailable() , ((Product)products[i]).getPrice());
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
                            //Writing to file
                            pw.println(write);
                        }
                    }
                    if (input == JOptionPane.OK_OPTION || input == JOptionPane.CANCEL_OPTION) {
                        //If user clicks cancel or ok close frame and go back previous menu
                        frame.dispose();
                        StoreOptions storeOptionsMenu = new StoreOptions();
                        storeOptionsMenu.run(storeName);
                    }
                    pw.close();
                    //Showing the Store that their products have been exported to the CSV file they inputted
                } catch (IOException e) {
                    //Changing image of JOptionPane
                    ImageIcon warning = new ImageIcon("WarningImage.png");
                    Image warningImage = warning.getImage();
                    //Resizing the image
                    Image resizedWarning = warningImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    warning = new ImageIcon(resizedWarning);
                    //Showing user warning message
                    input = JOptionPane.showConfirmDialog(null, "Enter a valid path",
                            "180-Market", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
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
