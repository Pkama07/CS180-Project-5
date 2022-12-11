import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.*;

/**
 * CreateNewStore.java
 *
 * Creates a new store associated with a Seller
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 3, 2022
 *
 */
public class CreateNewStore extends JComponent implements Runnable {
    TextField storeName;
    JButton createButton;
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
        storeName = new TextField("Store name");
        content.add(storeName);
        createButton = new JButton("Create");
        createButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(createButton , BorderLayout.SOUTH);
        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(backButton , BorderLayout.SOUTH);
        frame.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the Seller clicks the createButton
                //Create new store
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("username", Client.username);
                hm.put("storeName", storeName.getText());
                boolean response;
                try {
                    Client.out.writeObject(new Message("createStore", hm));
                    response = (boolean) Client.in.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //Calling the StoreMenu's run method
                StoreMenu storeMenu = new StoreMenu();
                frame.dispose();
                storeMenu.run();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //If the Seller clicks the backButton
                //Calling the StoreMenu's run method
                frame.dispose();
                StoreMenu storeMenu = new StoreMenu();
                storeMenu.run();
            }
        });
    }
}
