import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * ManageStore.java
 *
 * If the customer decides to click the manage store button from the StoreMenu class, they will be taken to this class
 * allowing them pick which store they would like to manage
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class ManageStore extends JComponent implements Runnable {
    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(200 , 200);
        frame.setLocationRelativeTo(null);
        //Ensures the frame will close
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("username", Client.username);
        Object[] stores;
        try {
            Client.out.writeObject(new Message("getStores", hm));
            stores = (Object[]) Client.in.readObject();
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        String[] storeNames = new String[stores.length];
        for ( int i = 0; i < stores.length; i++ ) {
           storeNames[i] = ((Store) stores[i]).getStoreName();
        }
        //Changing image of JOptionPane
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        //Asking the Seller which store they would like to manage
        String selectedStore = (String) JOptionPane.showInputDialog(frame ,
                "Which store would you like to manage?" , "180-Market" , JOptionPane.QUESTION_MESSAGE ,
                question , storeNames , storeNames[0]);
        if ( selectedStore == null ) {
            frame.dispose();
            return;
        }
        StoreOptions storeOptionsMenu = new StoreOptions();
        //CLosing frame and calling StoreOptions' run method
        frame.dispose();
        storeOptionsMenu.run(selectedStore);
    }
}
