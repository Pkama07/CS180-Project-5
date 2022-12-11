import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.*;

/**
 * ShowSales.java
 *
 * Shows the seller their sales and total revenue from a selected store
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class ShowSales extends JComponent implements Runnable {
    
    public void run() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", Client.username);
        Object[] stores;
        try {
            Client.out.writeObject(new Message("getStores", hm));
            stores = (Object[]) Client.in.readObject();
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        String[] storeNames = new String[stores.length];
        for ( int i = 0; i < stores.length; i++ ) {
           storeNames[i] = ((Store) stores[i]).getStoreName();
        }
        //Changing image of JOptionPane
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        //Asking the user which store they want their sales from
        String selectedStore = (String) JOptionPane.showInputDialog(null ,
                "Which store would you like to see sales from?" , "180-Market" ,
                JOptionPane.QUESTION_MESSAGE , question, storeNames , storeNames[0] );
        if ( selectedStore == null ) {
            return;
        }
        ArrayList<String> sales;
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("storeName", selectedStore);
        try {
            Client.out.writeObject(new Message("viewSales", m));
            sales = (ArrayList<String>) Client.in.readObject();
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        
        //Information is formatted in the following way:
        //sale#. productName    name of user who bought this item   revenue from this sale
        String result = "";
        int total = 0;
        int totalSales = 0;
        //Looping through sales ArrayList that is formatted in the following way:
        //buyerUserName,productInformation
        //productInformation is formatted as: "name/description/storeName/quantity/price"
        for ( int i = 0; i < sales.size(); i++ ) {
            //Splitting info at every ","
            String[] sale = sales.get(i).split(",");
            //Getting the buyer's userName
            String userName = sale[0];
            //Splitting up the product information at every "/"
            String[] productInfo = sale[1].split("/");
            //Getting the price of the product
            double price = Double.parseDouble(productInfo[4]);
            result += ( i + 1 ) + ".\t" + userName + "\t" + price + "\n";
            //Adding to total revenue and total sales number
            total += price * Double.valueOf(productInfo[3]);
            totalSales = i + 1;
        }
        //Making image of the JOptionPane to be the market's logo
        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
        Image logoImage = logo.getImage();
        //Resizing the image
        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        //Shows the seller their total sale and total revenue at the bottom
        int closed = JOptionPane.showConfirmDialog(null , result + "\nTotal Sales: " +
                totalSales + "\tTotal Revenue: " + total , "180-Market" , JOptionPane.OK_CANCEL_OPTION ,
                JOptionPane.INFORMATION_MESSAGE , logo);
        if ( closed == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.run();
    }
}
