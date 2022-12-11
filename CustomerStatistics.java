import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * CustomerStatistics.java
 *
 * Shows the customer their statistics, either sorted or unsorted
 *
 * @author Aniket Biswal and Pradyun Kamaraju, BLK
 *
 * @version Dec 7, 2022
 *
 */
public class CustomerStatistics extends JComponent implements Runnable {
    
    public void run() {
        boolean isSorted = false;
        String result = "";
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        int input = JOptionPane.showConfirmDialog(null , "Would you like your statistics sorted?"
                , "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , question );
        if ( input == JOptionPane.YES_OPTION ) {
            isSorted = true;
        }
        if ( input == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        // make request for statistics
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", Client.username);
        hm.put("isSorted", String.valueOf(isSorted));
        try {
            Client.out.writeObject(new Message("customerGetStatistics", hm));
            result = (String) Client.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
        Image logoImage = logo.getImage();
        //Resizing the image
        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        int closed = JOptionPane.showConfirmDialog(null , result , "180-Message" ,
                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
        if ( closed == JOptionPane.CANCEL_OPTION || closed == JOptionPane.OK_OPTION ) {
            CustomerMenu customerMenu = new CustomerMenu();
            customerMenu.run();
        }
        if ( closed == JOptionPane.CLOSED_OPTION ) {
            return;
        }
    }
}
