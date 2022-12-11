import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * SellerStatistics.java
 *
 * Shows the seller their statistics, either sorted or unsorted
 *
 * @author Adrienne Peters and Pradyun Kamaraju, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class SellerStatistics extends JComponent implements Runnable {
    public void run() {
        boolean isSorted = false;
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        int input = JOptionPane.showConfirmDialog(null , "Would you like your statistics sorted?"
                , "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , question);
        if ( input == JOptionPane.YES_OPTION ) {
            isSorted = true;
        }
        if ( input == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        String result;
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", Client.username);
        hm.put("isSorted", String.valueOf(isSorted));
        try {
            Client.out.writeObject(new Message("sellerGetStatistics", hm));
            result = (String) Client.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (result == null) {
            JOptionPane.showConfirmDialog(null ,
                    "There was an error reading from transactions.txt" , "180-Market" ,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Making image of the JOptionPane to be the market's logo
        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
        Image logoImage = logo.getImage();
        //Resizing the image
        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        int closed = JOptionPane.showConfirmDialog(null , result , "180-Message" ,
                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
        if ( closed == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.run();
    }
}
