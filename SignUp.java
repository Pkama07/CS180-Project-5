import org.w3c.dom.Text;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SignUp.java
 *
 * Starts the process of creating a new Seller or Customer account
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class SignUp extends JComponent implements Runnable {
    JButton customer;
    JButton seller;
    JButton backButton;
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
        //Making and formatting buttons
        customer = new JButton("I'm a customer");
        customer.setAlignmentX(CENTER_ALIGNMENT);
        customer.setMargin(new Insets(20 ,0 , 20 , 0));
        content.add(customer);
        seller = new JButton("I'm a seller");
        seller.setAlignmentX(CENTER_ALIGNMENT);
        seller.setMargin(new Insets(20 ,0 , 20 , 0));
        content.add(seller);
        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(backButton);
        frame.setVisible(true);
        customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the Customer button
                //Closing the frame and calling the SignUpInformation class's run method
                frame.dispose();
                SignUpInformation signUpInformationScreen = new SignUpInformation();
                Client.userType = "customer";
                signUpInformationScreen.run();
            }
        });
        seller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the user clicks the Seller button
                //Closing the frame and calling the SignUpInformation class's run method
                frame.dispose();
                SignUpInformation signUpInformationScreen = new SignUpInformation();
                Client.userType = "seller";
                signUpInformationScreen.run();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Go back to welcome screen
                Welcome welcomeScreen = new Welcome();
                frame.dispose();
                welcomeScreen.run();
            }
        });
    }
}
