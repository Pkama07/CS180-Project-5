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
 * SignUpInformation.java
 *
 * Creates the respective account types (given by the userType String) by prompting the user for
 * their username, password, and email
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class SignUpInformation extends JComponent implements Runnable {
    TextField email;
    TextField username;
    TextField password;
    JButton createAccount;
    JButton back;

    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(200 , 200);
        frame.setLocationRelativeTo(null);
        //Ensures the fame will close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        email = new TextField("Email");
        content.add(email);
        username = new TextField("Username");
        content.add(username);
        password = new TextField("Password");
        content.add(password);
        createAccount = new JButton("Create account");
        content.add(createAccount , BorderLayout.SOUTH);
        back = new JButton("Back");
        content.add(back , BorderLayout.SOUTH);
        frame.setVisible(true);
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the createAccount button
                //Changing image of JOptionPane
                ImageIcon question = new ImageIcon("QuestionMessage.png");
                Image questionImage = question.getImage();
                //Resizing the image
                Image resizedQuestion = questionImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                question = new ImageIcon(resizedQuestion);
                //Changing image of JOptionPane
                ImageIcon warning = new ImageIcon("WarningImage.png");
                Image warningImage = warning.getImage();
                //Resizing the image
                Image resizedWarning = warningImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                warning = new ImageIcon(resizedWarning);
                // Checking to see if email is already used in another account
                int closed = 100;
                String emailInput = email.getText();
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put(new String("email"), emailInput);
                String response;
                Message msg = new Message("checkEmailAvailability", hm);
                try {
                    Client.out.writeObject(msg);
                    response = (String) Client.in.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                if (response.equals("taken")) {
                    closed = JOptionPane.showConfirmDialog(null,
                            "This email already has an account linked with it!" +
                                    "\nPlease try another email\n", "180-Market",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                    if (closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                        frame.dispose();
                        return;
                    }
                }
                if (closed == JOptionPane.CANCEL_OPTION) { //If the user clicks cancel, go back to the welcome screen
                    frame.dispose();
                    Welcome welcomeScreen = new Welcome();
                    welcomeScreen.run();
                } else if (closed == JOptionPane.OK_OPTION) { //If the user clicks ok, go back to the start of the method
                    frame.dispose();
                    run();
                } else {
                    closed = 101;
                }
                if (closed == 101) { //If the user's email is valid
                    String userName = username.getText();
                    HashMap<String, String> nm = new HashMap<String, String>();
                    nm.put("username", userName);
                    try {
                        Client.out.writeObject(new Message("checkUsernameAvailability", nm));
                        response = (String) Client.in.readObject();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (response.equals("taken")) {
                        closed = JOptionPane.showConfirmDialog(null,
                                "This username already exists!" +
                                        "\nPlease try another username\n", "180-Market",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                        if (closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                            frame.dispose();
                            return;
                        }
                    }
                    if (closed == JOptionPane.CANCEL_OPTION) { //If the user clicks cancel, go back to the welcome screen
                        frame.dispose();
                        Welcome welcomeScreen = new Welcome();
                        welcomeScreen.run();
                    } else if (closed == JOptionPane.OK_OPTION) { //If the user clicks ok, go back to the start of the method
                        frame.dispose();
                        run();
                    } else {
                        closed = 102;
                    }
                    if (closed == 102) { //If the user's email and username is valid
                        //Asking user to confirm their password
                        String confirmPassword = (String) JOptionPane.showInputDialog(null,
                                "Confirm your password", "180-Market", JOptionPane.QUESTION_MESSAGE
                                , question, null, null);
                        if (confirmPassword == null) { //If the user closed or clicked cancel, return
                            frame.dispose();
                            return;
                        }
                        if (confirmPassword.equals(password.getText())) { //If the two passwords match
                            Client.username = userName;
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("email", emailInput);
                            m.put("username", userName);
                            m.put("password", password.getText());
                            if ( Client.userType.equals("customer") ) {
                                m.put("userType", "customer");
                                try {
                                    Client.out.writeObject(new Message("signUp", m));
                                    Client.in.readObject();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                CustomerMenu customerMenu = new CustomerMenu();
                                frame.dispose();
                                System.out.println("going back to customer menu");
                                customerMenu.run();
                            } else if (Client.userType.equals("seller")) {
                                m.put("userType", "seller");
                                try {
                                    Client.out.writeObject(new Message("signUp", m));
                                    Client.in.readObject();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                CreateNewStore createNewStoreMenu = new CreateNewStore();
                                frame.dispose();
                                createNewStoreMenu.run();
                            }
                        } else { //If the passwords do not match
                            //Notify the user that the passwords don't match
                            closed = JOptionPane.showConfirmDialog(null,
                                    "Password doesn't match", "180-Market",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, warning);
                            if (closed == JOptionPane.CANCEL_OPTION) { //If the user clicks cancel, go back to welcome
                                frame.dispose();
                                Welcome welcomeScreen = new Welcome();
                                welcomeScreen.run();
                            }
                            if (closed == JOptionPane.CLOSED_OPTION) { //If the frame is closed, return
                                frame.dispose();
                                return;
                            }
                            if (closed == JOptionPane.OK_OPTION) {
                                //If the user clicks okay, start method from beginning
                                frame.dispose();
                                run();
                            }
                        }
                    }
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go back to the signup screen
                SignUp signUpScreen = new SignUp();
                frame.dispose();
                signUpScreen.run();
            }
        });
    }
}
