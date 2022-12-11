import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

/**
 * StoreMenu.java
 *
 * If the currentUser is a Seller object then they will be directed to this class which shows actions the store can
 * take: manage store, view statistics, view sales, review account settings, and sign out
 *
 * @author Adrienne Peters, BLK
 *
 * @version Nov 29, 2022
 *
 */
public class StoreMenu extends JComponent implements Runnable {
    JButton manageStoresButton;
    JButton createNewStoreButton;
    JButton viewStatisticsButton;
    JButton viewSalesButton;
    JButton accountSettingsButton;
    JButton signOutButton;

    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensures the window closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Creating and formatting all buttons
        manageStoresButton = new JButton("Manage Stores");
        manageStoresButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(manageStoresButton);
        createNewStoreButton = new JButton("Create New Store");
        createNewStoreButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(createNewStoreButton);
        viewStatisticsButton = new JButton("View Statistics");
        viewStatisticsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(viewStatisticsButton);
        viewSalesButton = new JButton("View Sales");
        viewSalesButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(viewSalesButton);
        accountSettingsButton = new JButton("Account Settings");
        accountSettingsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(accountSettingsButton);
        signOutButton = new JButton("Sign Out");
        signOutButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(signOutButton);
        frame.setVisible(true);
        //Adding action to each of the buttons
        //If the user clicks the manageStoreButton
        manageStoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//If the user clicks the manageStoreButton
                //Close the frame
                frame.dispose();
                //Call ManageStore class's run method and send it the current user
                ManageStore manageStoreMenu = new ManageStore();
                manageStoreMenu.run();
            }
        });
        createNewStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close the frame
                frame.dispose();
                //Call CreateNewStore class's run method and send it currentUser
                CreateNewStore createNewStoreMenu = new CreateNewStore();
                createNewStoreMenu.run();
            }
        });
        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//If the user clicks the viewStatisticsButton
                //Close the frame
                frame.dispose();
                //Call ViewStatistics class's run method and send it the current user
                SellerStatistics sellerStatisticsMenu = new SellerStatistics();
                sellerStatisticsMenu.run();
            }
        });
        viewSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//If the user clicks the viewSalesButton
                //Close the frame
                frame.dispose();
                //Call ViewSales class's run method and send it the current user
                ShowSales showSalesMenu = new ShowSales();
                showSalesMenu.run();
            }
        });
        accountSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//If the user clicks the accountSettings
                //Close the frame
                frame.dispose();
                //Call AccountSettings class's run method and send it the current user
                AccountSettings accountSettingsMenu = new AccountSettings();
                accountSettingsMenu.run();
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close the frame
                frame.dispose();
                //Making image of the JOptionPane to be the market's logo
                ImageIcon logo = new ImageIcon("180-MarketLogo@500x-8.png");
                Image logoImage = logo.getImage();
                //Resizing the image
                Image resizedLogo = logoImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                logo = new ImageIcon(resizedLogo);
                //Thanks the user;
                JOptionPane.showConfirmDialog(null , "Thank you for using 180-Market" ,
                        "Goodbye" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , logo);
                return;
            }
        });
    }
}
