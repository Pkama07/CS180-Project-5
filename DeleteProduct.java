import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * DeleteProduct.java
 *
 * Allows the seller to delete products from their store
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class DeleteProduct extends JComponent implements Runnable {
    public void run() {
        return;
    }
    public void run(String storeName) {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //Ensuring the frame closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
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
        String[] productNames = new String[products.length];
        for (int i = 0; i<products.length; i++) {
            productNames[i] = ((Product) products[i]).getName();
        }
        //Changing image of JOptionPane
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        //Asking which product they would like to delete
        String selectedProduct = (String) JOptionPane.showInputDialog(frame ,
                "Which product would you like to delete?", "180-Market", JOptionPane.QUESTION_MESSAGE,
                question, productNames, productNames[0]);
        frame.setVisible(true);
        if (selectedProduct == null) {
            frame.dispose();
            return;
        }
        //Asking user to confirm they want to delete the product
        int confirm = JOptionPane.showConfirmDialog(frame , "Are you sure you want to delete " +
                selectedProduct + "?" , "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE
                , question);
        if ( confirm  == JOptionPane.YES_OPTION ) { //If they confirm they want to delete the product
            HashMap<String, String> m = new HashMap<String, String>();
            m.put("storeName", storeName);
            m.put("productName", selectedProduct);
            try {
                Client.out.writeObject(new Message("deleteProduct", m));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Go back to the storeOptionsMenu
            StoreOptions storeOptionsMenu = new StoreOptions();
            storeOptionsMenu.run(storeName);
        } else if ( confirm == JOptionPane.NO_OPTION ) { //If the user doesn't confirm
            //Close the frame and start the method over
            frame.dispose();
            run(storeName);
        } else if ( confirm == JOptionPane.CLOSED_OPTION ) { //If the user closes the frame
            //Return
            frame.dispose();
            return;
        }
        frame.dispose();
        StoreOptions storeOptionsMenu = new StoreOptions();
        storeOptionsMenu.run(storeName);
    }
}
