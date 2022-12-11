import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * Welcome.java
 *
 * Displays welcome menu to the 180-Market
 *
 * Set alignment and boxLayout info found at:
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/index.html#BoxLayoutDemo
 * ImageIcon info found at:
 * https://docs.oracle.com/javase/6/docs/api/javax/swing/JOptionPane.html#JOptionPane(java.lang.Object,%20int,%20int,%20
 * javax.swing.Icon)
 *
 * @author Adrienne Peters, BLK
 *
 * @version Nov 29, 2022
 *
 */
public class Welcome extends JComponent implements Runnable{
    JButton signIn;
    JButton signUp;
    TextArea welcome;
    
    
    public Welcome() {
    }

    @Override
    public void run() {
        //Making new frame with title "180-Market" and width/height 200 pixels
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(200 , 200);
        frame.setLocationRelativeTo(null);
        //Ensure that the frame closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Making image of the JOptionPane to be the market's logo
        ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
        Image logoImage = logo.getImage();
        //Resizing the image
        Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        //Welcoming user
        int welcome = JOptionPane.showConfirmDialog(null , "Welcome to 180-Market" ,
                "180-Market" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
        if ( welcome == JOptionPane.CANCEL_OPTION || welcome == JOptionPane.CLOSED_OPTION ) {
            return;
        }
        //Making user pick between Signing in and signing up
        signIn = new JButton("Sign-in");
        //Formatting button
        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signIn.setMargin(new Insets(25 , 50 , 25 ,50));
        content.add(signIn);
        signUp = new JButton("Sign-up");
        //Formatting button
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUp.setMargin(new Insets(25 , 50 , 25 ,50));
        content.add(signUp);
        frame.setVisible(true);
        //Adding action to be preformed when the user clicks the signIn button
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Closing current frame
                frame.dispose();
                //Calling logInScreen.java's run method
                LogIn logInScreen = new LogIn();
                logInScreen.run();
            }
        });
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUp signUpScreen = new SignUp();
                signUpScreen.run();
            }
        });
    }
}
