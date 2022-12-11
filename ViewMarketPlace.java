import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ViewMarketPlace.java
 *
 * This class allows for customers to view all the products available for purchase. The customer has to
 * option to search by product name and also by store name
 *
 * @author Aniket Biswal, BLK
 *
 * @version Dec 7, 2022
 *
 */
public class ViewMarketPlace extends JComponent implements Runnable {
    JButton[] buttons;
    JButton backButton;
    public ViewMarketPlace() {
    }


    public void run(Product[] products) {
        //Making frame
        System.out.println("showing marketplace");
        JFrame frame = new JFrame();
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting up panels, buttons, scrollPanes, and textFields
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel , BoxLayout.Y_AXIS));
        JPanel productPanel = new JPanel();
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container , BoxLayout.Y_AXIS));
        Container content = frame.getContentPane();
        JPanel searchProductPanel = new JPanel();
        JPanel searchStorePanel = new JPanel();
        JTextField searchProductField = new JTextField(10);
        JButton searchProductButton = new JButton("Search Product Name");
        JTextField searchStoreField = new JTextField(10);
        JButton searchStoreButton = new JButton("Search Store Name");
        JButton filterButton = new JButton("Filter");
        searchProductPanel.add(searchProductField);
        searchProductPanel.add(searchProductButton);
        searchStorePanel.add(searchStoreField);
        searchStorePanel.add(searchStoreButton);
        searchPanel.add(searchProductPanel);
        searchPanel.add(searchStorePanel);
        searchPanel.add(filterButton);
        container.add(searchPanel);
        String[] productNames = new String[products.length];
        for (int i = 0; i<products.length; i++) {
            productNames[i] = products[i].getName();
        }
        buttons = new JButton[products.length];
        Panel productButtons = new Panel(new GridLayout((buttons.length + 3 - buttons.length % 3) / 3, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(productNames[i]);
            buttons[i].setMargin(new Insets(20 , 5 , 20 , 5));
            productButtons.add(buttons[i]);
        }
        productPanel.add(productButtons);
        container.add(productPanel);
        JPanel backButtonPanel = new JPanel();
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        JScrollPane scrollPane = new JScrollPane(container , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        content.add(scrollPane);
        content.add(backButtonPanel , BorderLayout.SOUTH);
        frame.setVisible(true);
        for (int i = 0; i < buttons.length; i++) {
            int k = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowProductPane showProductPane = new ShowProductPane();
                    showProductPane.run(products[k].getName(), products[k].getStore().getSeller().getUserName(), products[k].getStore().getStoreName(), products[k].getDescription(), products[k].getQuantityAvailable(), products[k].getPrice());
                }
            });
        }
        searchProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search searchProducts = new Search();
                String productName = searchProductField.getText();
                frame.dispose();
                searchProducts.run(1, productName);
            }
        });

        searchStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search searchStore = new Search();
                String storeName = searchStoreField.getText();
                frame.dispose();
                searchStore.run(2, storeName);
            }
        });
        filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sort sortMenu = new Sort();
				frame.dispose();
				sortMenu.run();
			}
		});
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.run();
            }
        });
    }

    public void run() {
        //Making frame
        JFrame frame = new JFrame();
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting up panels, buttons, scrollPanes, and textFields
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel , BoxLayout.Y_AXIS));
        JPanel productPanel = new JPanel();
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container , BoxLayout.Y_AXIS));
        Container content = frame.getContentPane();
        JPanel searchProductPanel = new JPanel();
        JPanel searchStorePanel = new JPanel();
        JTextField searchProductField = new JTextField(10);
        JButton searchProductButton = new JButton("Search Product Name");
        JTextField searchStoreField = new JTextField(10);
        JButton searchStoreButton = new JButton("Search Store Name");
        JButton filterButton = new JButton("Filter");
        searchProductPanel.add(searchProductField);
        searchProductPanel.add(searchProductButton);
        searchStorePanel.add(searchStoreField);
        searchStorePanel.add(searchStoreButton);
        searchPanel.add(searchProductPanel);
        searchPanel.add(searchStorePanel);
        searchPanel.add(filterButton);
        container.add(searchPanel);
        HashMap<String, String> hm = new HashMap<String, String>();
        Object[] products;
        try {
            Client.out.writeObject(new Message("getAllProducts", hm));
            products = (Object[]) Client.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String[] productNames = new String[products.length];
        for (int i = 0; i<products.length; i++) {
            productNames[i] = ((Product) products[i]).getName();
        }
        buttons = new JButton[products.length];
        Panel productButtons = new Panel(new GridLayout((buttons.length + 3 - buttons.length % 3) / 3, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(productNames[i]);
            buttons[i].setMargin(new Insets(20 , 5 , 20 , 5));
            productButtons.add(buttons[i]);
        }
        productPanel.add(productButtons);
        container.add(productPanel);
        JPanel backButtonPanel = new JPanel();
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        JScrollPane scrollPane = new JScrollPane(container , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        content.add(scrollPane);
        content.add(backButtonPanel , BorderLayout.SOUTH);
        frame.setVisible(true);
        for (int i = 0; i < buttons.length; i++) {
            int k = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowProductPane showProductPane = new ShowProductPane();
                    Product prod = (Product) products[k];
                    showProductPane.run(prod.getName(), prod.getStore().getSeller().getUserName(), prod.getStore().getStoreName(), prod.getDescription(), prod.getQuantityAvailable(), prod.getPrice());
                }
            });
        }
        searchProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search searchProducts = new Search();
                String productName = searchProductField.getText();
                frame.dispose();
                searchProducts.run(1, productName);
            }
        });

        searchStoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Search searchStore = new Search();
                String storeName = searchStoreField.getText();
                frame.dispose();
                searchStore.run(2, storeName);
            }
        });
        filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sort sortMenu = new Sort();
				frame.dispose();
				sortMenu.run();
			}
		});
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.run();
            }
        });
    }


}
