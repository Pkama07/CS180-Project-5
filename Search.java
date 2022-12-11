import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Search extends JComponent implements Runnable {
    JButton[] buttons;
    JButton backButton;
    public void run() {
        return;
    }

    public void run(int n , String name) {
        JFrame frame = new JFrame();
        JPanel container = new JPanel();
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        if (n == 1) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("descriptor", "name");
            hm.put("value", name);
            Object[] products;
            try {
                Client.out.writeObject(new Message("searchProducts", hm));
                products = (Object[]) Client.in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            /*buttons = new JButton[products.size()];
            JPanel productButtons = new JPanel();
            productButtons.setLayout(new BoxLayout(productButtons , BoxLayout.Y_AXIS));
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(products.get(i).getName());
                buttons[i].setMargin(new Insets(20 , 5 , 20 , 5));
                productButtons.add(buttons[i]);
            }
            container.add(productButtons);
            JScrollPane scrollPane = new JScrollPane(container , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            content.add(scrollPane);
            JPanel backButtonPanel = new JPanel();
            backButton = new JButton("Back");
            backButtonPanel.add(backButton);
            content.add(backButtonPanel , BorderLayout.SOUTH);
            frame.setVisible(true);
            for (int i = 0; i < buttons.length; i++) {
                String productString = String.format("Name: %s\nStore: %s\nDescription: " +
                                "%s\nQuantity: %d\nPrice: %.2f", products.get(i).getName() , products.get(i).getStore().getStoreName() ,
                        products.get(i).getDescription() , products.get(i).getQuantityAvailable() , products.get(i).getPrice());
                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showConfirmDialog(null , productString , "180 Market" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);    			}
                });
            }*/
            ViewMarketPlace marketMenu = new ViewMarketPlace();
            Product[] products2 = new Product[products.length];
            for (int i = 0; i<products.length; i++) {
                products2[i] = (Product) products[i];
            }
			marketMenu.run((Product[]) products2);
        } else {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("descriptor", "store");
            hm.put("value", name);
            Object[] products;
            try {
                Client.out.writeObject(new Message("searchProducts", hm));
                products = (Object[]) Client.in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            /*buttons = new JButton[products.size()];
            JPanel productButtons = new JPanel();
            productButtons.setLayout(new BoxLayout(productButtons , BoxLayout.Y_AXIS));
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(products.get(i).getName());
                buttons[i].setMargin(new Insets(20 , 5 , 20 , 5));
                productButtons.add(buttons[i]);
            }
            container.add(productButtons);
            JScrollPane scrollPane = new JScrollPane(container , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            content.add(scrollPane);
            JPanel backButtonPanel = new JPanel();
            backButton = new JButton("Back");
            backButtonPanel.add(backButton);
            content.add(backButtonPanel , BorderLayout.SOUTH);
            frame.setVisible(true);
            for (int i = 0; i < buttons.length; i++) {
                String productString = String.format("Name: %s\nStore: %s\nDescription: " +
                                "%s\nQuantity: %d\nPrice: %.2f", products.get(i).getName() , products.get(i).getStore().getStoreName() ,
                        products.get(i).getDescription() , products.get(i).getQuantityAvailable() , products.get(i).getPrice());
                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showConfirmDialog(null , productString , "180 Market" , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);    			}
                });
            }*/
            ViewMarketPlace marketMenu = new ViewMarketPlace();
			Product[] products2 = new Product[products.length];
            for (int i = 0; i<products.length; i++) {
                products2[i] = (Product) products[i];
            }
			marketMenu.run((Product[]) products2);
        }
        /*backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.run(currentUser , writer);
            }
        });*/
    }

}
