import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * LogIn.java
 *
 * Logs the user in with the given user-name and password
 *
 * @author Adrienne Peters, BLK
 *
 * @version Nov 29, 2022
 *
 */
public class LogIn extends JComponent implements Runnable{
    TextField userName;
    TextField passWord;
    JButton logInButton;
    JButton backButton;
    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensures the frame will close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Making username and password TextFields
        userName = new TextField();
        userName.setText("Username");
        content.add(userName);
        passWord = new TextField();
        passWord.setText("Password");
        content.add(passWord);
        //Creating Panel to hold the login and back buttons
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.X_AXIS));
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        logInButton = new JButton("Login");
        buttonPanel.add(logInButton);
        content.add(buttonPanel);
        frame.setVisible(true);
        //Adding action to be preformed if user clicks the login button
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response;
                //Loop continues until the user enters a username/password combination that is correct
                do {
                    //Getting text from username and password TextFields
                    String username = userName.getText();
                    String password = passWord.getText();
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("username", username);
                    hm.put("password", password);
                    Message msg = new Message("signIn", hm);
                    try {
                        Client.out.writeObject(msg);
                        response = (String) Client.in.readObject();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return;
                    }
                    //If neither of the above cases are met then the user entered the wrong username/password
                    if (response.equals("none")) {
                        int closed;
                        //Shows error message if username/password is incorrect
                        //Changing image of JOptionPane
                        ImageIcon warning = new ImageIcon("WarningImage.png");
                        Image warningImage = warning.getImage();
                        //Resizing the image
                        Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                        warning = new ImageIcon(resizedWarning);
                        closed = JOptionPane.showConfirmDialog(null,
                                "Please check your username and/or password again", "180-Market",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE , warning);
                        if ( closed == JOptionPane.CANCEL_OPTION || closed == JOptionPane.CLOSED_OPTION) {
                            return;
                        }
                        response = "nothing";
                    } else {
                        Client.username = username;
                    }
                } while (response.equals("none"));
                //Calling the respective object's run method
                if (response.equals("seller")) {
                    frame.dispose();
                    Client.userType = "seller";
                    StoreMenu storeMenu = new StoreMenu();
                    storeMenu.run();
                } else if (response.equals("customer")) {
                   frame.dispose();
                   Client.userType = "customer";
                   CustomerMenu customerMenu= new CustomerMenu();
                   customerMenu.run();
                } else { //If the object is null then close the frame and call this run method again
                    frame.dispose();
                    run();
                }
            }
        });
        //Adding action to be preformed if user clicks the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close this frame and call the Welcome class's run method
                frame.dispose();
                Welcome welcomeScreen = new Welcome();
                welcomeScreen.run();
            }
        });
    }
}
