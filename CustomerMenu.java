import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Customer.java
 *
 * If the currentUser is a Customer object then they will be directed to this class which shows actions the customer
 * can take: view marketplace, view cart and history, view statistics, review account settings,
 * and sign out. After the Seller clicks a button they are taken to the respective class's run method
 *
 * @author Aniket Biswal and Adrienne Peters, BLK
 *
 * @version Dec 7, 2022
 *
 */
public class CustomerMenu extends JComponent implements Runnable {
    JButton viewMarketplaceButton;
    JButton cartAndHistoryButton;
    JButton accountSettingsButton;
    JButton viewStatisticsButton;
    JButton signOutButton;
    public CustomerMenu() {
    }
    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring it closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Formatting and making button
        viewMarketplaceButton = new JButton("View Marketplace");
        viewMarketplaceButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(viewMarketplaceButton);
        cartAndHistoryButton = new JButton("Cart & History");
        cartAndHistoryButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(cartAndHistoryButton);
        viewStatisticsButton = new JButton("View Statistics");
        viewStatisticsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(viewStatisticsButton);
        accountSettingsButton = new JButton("Account Settings");
        accountSettingsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(accountSettingsButton);
        signOutButton = new JButton("Sign Out");
        signOutButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(signOutButton);
        frame.setVisible(true);
        
        viewMarketplaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewMarketPlace marketMenu = new ViewMarketPlace();
				frame.dispose();
				marketMenu.run();
			}
		});

        
        cartAndHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CartAndPurchaseHistory cartAndPurchaseHistoryMenu = new CartAndPurchaseHistory();
				frame.dispose();
				cartAndPurchaseHistoryMenu.run();
			}
		});
        
        viewStatisticsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerStatistics stats = new CustomerStatistics();
				frame.dispose();
				stats.run();
			}
		});
        
        accountSettingsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { //If the Customer clicks the accountSettingsButton
        		//Call AccountSetting's run method
        		AccountSettings accountSettingsMenu = new AccountSettings();
        		frame.dispose();
        		accountSettingsMenu.run();
        	}

        });
        
        signOutButton.addActionListener(new ActionListener() {
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
