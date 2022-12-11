import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * AccountSettings.java
 *
 * Changes the username, password, or email of the user
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class AccountSettings extends JComponent implements Runnable {
    JButton changeButton;
    JButton changeUsernameButton;
    TextField newUsername;
    TextField confirmUsername;
    JButton changeEmailButton;
    TextField newEmail;
    TextField confirmEmail;
    JButton changePasswordButton;
    TextField newPassword;
    TextField confirmPassword;
    JButton deleteAccountButton;
    JButton backButton;
    public void run() {
        //Creating frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensures the frame closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Creating and formatting the buttons and textFields
        changeUsernameButton = new JButton("Change username");
        changeUsernameButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(changeUsernameButton);
        changeEmailButton = new JButton("Change email");
        changeEmailButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(changeEmailButton);
        changePasswordButton = new JButton("Change password");
        changePasswordButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(changePasswordButton);
        deleteAccountButton = new JButton("Delete account");
        deleteAccountButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(deleteAccountButton);
        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(backButton);
        frame.setVisible(true);
        changeUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user click the changeUserNameButton
                //Set up a new frame
                JFrame frame1 = new JFrame();
                frame1.setTitle("180 Market");
                frame1.setSize(400 , 200);
                frame1.setLocationRelativeTo(null);
                //Ensusres the new frame will close
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Container content = frame1.getContentPane();
                content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
                //Creating and formatting buttons and textFields
                newUsername = new TextField("New username");
                content.add(newUsername);
                confirmUsername = new TextField("Confirm username");
                content.add(confirmUsername);
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(changeButton , BorderLayout.SOUTH);
                backButton = new JButton("Back");
                backButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(backButton , BorderLayout.SOUTH);
                //Closing the first frame and showing the new frame
                frame.dispose();
                frame1.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        if ( newUsername.getText().equals(confirmUsername.getText())) { //If the usernames match
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("username", Client.username);
                            Client.username = newUsername.getText();
                            hm.put("newUsername", newUsername.getText());
                            try {
                                Client.out.writeObject(new Message("changeUsername", hm));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //Making image of the JOptionPane to be the market's logo
                            ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                            Image logoImage = logo.getImage();
                            //Resizing the image
                            Image resizedLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            logo = new ImageIcon(resizedLogo);
                            frame1.dispose();
                            //Notifying user that the username has been changed
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your username has been changed!", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, logo);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            if (Client.userType.equals("customer")) { //If the user is a Customer, go back to their menu
                                CustomerMenu customerMenu = new CustomerMenu();
                                customerMenu.run();
                            } else { //If the user is a Seller, go back to their menu
                                StoreMenu storeMenu = new StoreMenu();
                                storeMenu.run();
                            }
                        } else { //If the usernames don't match
                            //Changing image of JOptionPane
                            ImageIcon warning = new ImageIcon("WarningImage.png");
                            Image warningImage = warning.getImage();
                            //Resizing the image
                            Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                            warning = new ImageIcon(resizedWarning);
                            //Notifying the user that the usernames don't match
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your usernames don't match!\nPlease try again", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            frame1.dispose();
                            run();
                        }
                    }
                });
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Closing the frame
                        frame1.dispose();
                        //Start run method from beginning
                        run();
                    }
                });
            }
        });
        changeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the changeEmailButton
                //Set up a new frame
                JFrame frame1 = new JFrame();
                frame1.setTitle("180 Market");
                frame1.setSize(400 , 200);
                frame1.setLocationRelativeTo(null);
                //Ensusres the new frame will close
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Container content = frame1.getContentPane();
                content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
                //Creating and formatting buttons and textFields
                newEmail = new TextField("New email");
                content.add(newEmail);
                confirmEmail = new TextField("Confirm email");
                content.add(confirmEmail);
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(changeButton , BorderLayout.SOUTH);
                backButton = new JButton("Back");
                backButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(backButton , BorderLayout.SOUTH);
                //Closing the first frame and showing the new frame
                frame.dispose();
                frame1.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        if ( newEmail.getText().equals(confirmEmail.getText())) { //If the emails match
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("username", Client.username);
                            hm.put("newEmail", newEmail.getText());
                            try {
                                Client.out.writeObject(new Message("changeEmail", hm));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //Making image of the JOptionPane to be the market's logo
                            ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                            Image logoImage = logo.getImage();
                            //Resizing the image
                            Image resizedLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            logo = new ImageIcon(resizedLogo);
                            frame1.dispose();
                            //Notifying user that the username has been changed
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your email has been changed!", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, logo);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            if (Client.userType.equals("customer")) { //If the user is a Customer, go back to their menu
                                CustomerMenu customerMenu = new CustomerMenu();
                                customerMenu.run();
                            } else { //If the user is a Seller, go back to their menu
                                StoreMenu storeMenu = new StoreMenu();
                                storeMenu.run();
                            }
                        } else { //If the emails don't match
                            //Changing image of JOptionPane
                            ImageIcon warning = new ImageIcon("WarningImage.png");
                            Image warningImage = warning.getImage();
                            //Resizing the image
                            Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                            warning = new ImageIcon(resizedWarning);
                            //Notifying the user that the emails don't match
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your emails don't match!\nPlease try again", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            frame1.dispose();
                            run();
                        }
                    }
                });
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Closing the frame
                        frame1.dispose();
                        //Start run method from beginning
                        run();
                    }
                });
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the changePasswordButton
                //Set up a new frame
                JFrame frame1 = new JFrame();
                frame1.setTitle("180 Market");
                frame1.setSize(400 , 200);
                frame1.setLocationRelativeTo(null);
                //Ensusres the new frame will close
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Container content = frame1.getContentPane();
                content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
                //Creating and formatting buttons and textFields
                newPassword = new TextField("New password");
                content.add(newPassword);
                confirmPassword = new TextField("Confirm password");
                content.add(confirmPassword);
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(changeButton , BorderLayout.SOUTH);
                backButton = new JButton("Back");
                backButton.setAlignmentX(CENTER_ALIGNMENT);
                content.add(backButton , BorderLayout.SOUTH);
                //Closing the first frame and showing the new frame
                frame.dispose();
                frame1.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        if ( newPassword.getText().equals(confirmPassword.getText())) { //If the passwords match
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("username", Client.username);
                            hm.put("newPassword", newPassword.getText());
                            try {
                                Client.out.writeObject(new Message("changeEmail", hm));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //Making image of the JOptionPane to be the market's logo
                            ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                            Image logoImage = logo.getImage();
                            //Resizing the image
                            Image resizedLogo = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            logo = new ImageIcon(resizedLogo);
                            frame1.dispose();
                            //Notifying user that the username has been changed
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your password has been changed!", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, logo);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            if (Client.userType.equals("customer")) { //If the user is a Customer, go back to their menu
                                CustomerMenu customerMenu = new CustomerMenu();
                                customerMenu.run();
                            } else { //If the user is a Seller, go back to their menu
                                StoreMenu storeMenu = new StoreMenu();
                                storeMenu.run();
                            }
                        } else { //If the passwords don't match
                            //Changing image of JOptionPane
                            ImageIcon warning = new ImageIcon("WarningImage.png");
                            Image warningImage = warning.getImage();
                            //Resizing the image
                            Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                            warning = new ImageIcon(resizedWarning);
                            //Notifying the user that the passwords don't match
                            int closed = JOptionPane.showConfirmDialog(null,
                                    "Your passwords don't match!\nPlease try again", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                            if ( closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                return;
                            }
                            frame1.dispose();
                            run();
                        }
                    }
                });
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Closing the frame
                        frame1.dispose();
                        //Start run method from beginning
                        run();
                    }
                });
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Closing the frame
                frame.dispose();
                //Calling currentUser's menu
                if (Client.userType.equals("customer")) { //If the user is a Customer, go back to their menu
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.run();
                } else { //If the user is a Seller, go back to their menu
                    StoreMenu storeMenu = new StoreMenu();
                    storeMenu.run();
                }
            }
        });
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Changing image of JOptionPane
                ImageIcon warning = new ImageIcon("WarningImage.png");
                Image warningImage = warning.getImage();
                //Resizing the image
                Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                warning = new ImageIcon(resizedWarning);
                //Asking user if they are sure they want to delete their account
                int confirm = JOptionPane.showConfirmDialog(null ,
                        "Are you sure you want to delete your account?" , "180-Market" ,
                        JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE , warning);
                if ( confirm == JOptionPane.NO_OPTION) { //If they say no
                    if (Client.userType.equals("customer")) { //If the user is a Customer, go back to their menu
                        CustomerMenu customerMenu = new CustomerMenu();
                        frame.dispose();
                        customerMenu.run();
                    } else { //If the user is a Seller, go back to their menu
                        StoreMenu storeMenu = new StoreMenu();
                        frame.dispose();
                        storeMenu.run();
                    }
                } else {
                    if (Client.userType.equals("seller")) {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("username", Client.username);
                        Object[] stores;
                        try {
                            Client.out.writeObject(new Message("getStores", hm));
                            stores = (Object[]) Client.in.readObject();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        for (Object st: stores) {
                            Store str = (Store) st;
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("storeName", str.getStoreName());
                            try {
                                Client.out.writeObject(new Message("deleteStore", m));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        try {
                            Client.out.writeObject(new Message("deleteUser", hm));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //Making image of the JOptionPane to be the market's logo
                        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                        Image logoImage = logo.getImage();
                        //Resizing the image
                        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                        logo = new ImageIcon(resizedLogo);
                        frame.dispose();
                        //Notifying the user that their account has been deleted
                        int closed = JOptionPane.showConfirmDialog(null ,
                                "Your account has been deleted" , "180-Market" ,
                                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                        //If they close the frame, then return
                        if ( closed == JOptionPane.CLOSED_OPTION ) {
                            return;
                        }
                        //Goodbye message
                        JOptionPane.showConfirmDialog(null , "Thank you for using 180-Market" ,
                                "Goodbye" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                        return;
                    } else {
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("username", Client.username);
                        try {
                            Client.out.writeObject(new Message("deleteUser", hm));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //Making image of the JOptionPane to be the market's logo
                        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                        Image logoImage = logo.getImage();
                        //Resizing the image
                        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                        logo = new ImageIcon(resizedLogo);
                        frame.dispose();
                        //Notifying the user that their account has been deleted
                        int closed = JOptionPane.showConfirmDialog(null ,
                                "Your account has been deleted" , "180-Market" ,
                                JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                        //If they close the frame, then return
                        if ( closed == JOptionPane.CLOSED_OPTION ) {
                            return;
                        }
                        //Goodbye message
                        JOptionPane.showConfirmDialog(null , "Thank you for using 180-Market" ,
                                "Goodbye" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                        return;
                    }
                }
            }
        });
    }
}
