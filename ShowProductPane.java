import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * CustomerStatistics.java
 *
 * This class is a JFrame formatted to look like a JOptionPane with an added "Add to Cart" button
 *
 * @author Adrienne Peters and Aniket Biswal, BLK
 *
 * @version Dec 7, 2022
 *
 */
public class ShowProductPane extends JComponent implements Runnable {
	//JButton okButton;
    JButton backButton;
    JButton addToCartButton;
    TextField productInfo;
	public ShowProductPane() {}
    public void run() {}
	
	public void run(String productName, String sellerName, String storeName, String productDescription, int quantityi, double pricei) {
		//Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring the window closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        /*okButton = new JButton("Ok");
        buttons.add(okButton);*/
        backButton = new JButton("Back");
        buttons.add(backButton);
        addToCartButton = new JButton("Add to cart");
        buttons.add(addToCartButton);
        content.add(buttons , BorderLayout.PAGE_END);
        JPanel productInfo = new JPanel();
        productInfo.setLayout(new BoxLayout(productInfo , BoxLayout.Y_AXIS));
        TextField name = new TextField("Name: " + productName);
        name.setEditable(false);
        productInfo.add(name);
        TextField description = new TextField("Description: " + productDescription);
        description.setEditable(false);
        productInfo.add(description);
        TextField quantity = new TextField("Quantity: " + quantityi);
        quantity.setEditable(false);
        productInfo.add(quantity);
        TextField price = new TextField("Price: " + pricei);
        price.setEditable(false);
        productInfo.add(price);
        content.add(productInfo , BorderLayout.EAST);
        frame.setVisible(true);

        addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Integer[] qAvil = new Integer[quantityi];
                for ( int i = 0; i < quantityi; i++ ) {
                    qAvil[i] = i + 1;
                }
                ImageIcon question = new ImageIcon("QuestionMessage.png");
                Image questionImage = question.getImage();
                //Resizing the image
                Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                question = new ImageIcon(resizedQuestion);
                Integer q = (Integer) JOptionPane.showInputDialog(null , "How many" +
                        productName+"s would you like to add to your shopping cart?" , "180-Market" ,
                        JOptionPane.QUESTION_MESSAGE , question , qAvil , qAvil[0]);
                if ( q == null ) {
                    return;
                }
                //Use the addToShoppingCart method in Customer
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("username", Client.username);
                hm.put("sellerName", sellerName);
                hm.put("storeName", storeName);
                hm.put("productName", productName);
                hm.put("quantity", String.valueOf(q));
                boolean response;
                try {
                    Client.out.writeObject(new Message("addProducts", hm));
                    response = (boolean) Client.in.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
			}
		});
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
	}
}
