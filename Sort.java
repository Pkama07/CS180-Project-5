import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Sort extends JComponent implements Runnable {
	public Sort() {}
	
	public void run() {
		System.out.println("starting sort");
		JFrame frame = new JFrame();
		frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new GridLayout(2, 2));
        JButton priceLtoH = new JButton("Price Low to High");
        JButton priceHtoL = new JButton("Price High to Low");
        JButton quantityLtoH = new JButton("Quantity Low to High");
        JButton quantityHtoL = new JButton("Quantity High to Low");
        content.add(priceLtoH);
        content.add(priceHtoL);
        content.add(quantityLtoH);
        content.add(quantityHtoL);
		frame.setVisible(true);
        HashMap<String, String> hm = new HashMap<String, String>();
        Object[] productsAr;
        try {
            Client.out.writeObject(new Message("getAllProducts", hm));
            productsAr = (Object[]) Client.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		Product[] productsArr = new Product[productsAr.length];
		for (int i = 0; i<productsAr.length; i++) {
			productsArr[i] = (Product) productsAr[i];
		}
        priceLtoH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < productsArr.length; i++) {
					for (int j = 1; j < productsArr.length; j++) {
						if (productsArr[j].getPrice() < productsArr[i].getPrice()) {
							Product temp = productsArr[i];
							productsArr[i] = productsArr[j];
							productsArr[j] = temp;
						}					
					}
				}
				ArrayList<Product> sortedProducts = new ArrayList<Product>();
				for (int i = 0; i < productsArr.length; i++) {
					sortedProducts.add(productsArr[i]);
				}
				ViewMarketPlace marketMenu = new ViewMarketPlace();
				frame.dispose();
				marketMenu.run(sortedProducts.toArray(new Product[0]));

			}
		});
        
        priceHtoL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < productsArr.length; i++) {
					for (int j = 1; j < productsArr.length; j++) {
						if (productsArr[j].getPrice() > productsArr[i].getPrice()) {
							Product temp = productsArr[i];
							productsArr[i] = productsArr[j];
							productsArr[j] = temp;
						}					
					}
				}
				ArrayList<Product> sortedProducts = new ArrayList<Product>();
				for (int i = 0; i < productsArr.length; i++) {
					sortedProducts.add(productsArr[i]);
				}
				ViewMarketPlace marketMenu = new ViewMarketPlace();
				frame.dispose();
				System.out.println(sortedProducts.size());
				marketMenu.run(sortedProducts.toArray(new Product[0]));
			}
		});
        
        quantityLtoH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < productsArr.length; i++) {
					for (int j = 1; j < productsArr.length; j++) {
						if (productsArr[j].getQuantityAvailable() < productsArr[i].getQuantityAvailable()) {
							Product temp = productsArr[i];
							productsArr[i] = productsArr[j];
							productsArr[j] = temp;
						}					
					}
				}
				ArrayList<Product> sortedProducts = new ArrayList<Product>();
				for (int i = 0; i < productsArr.length; i++) {
					sortedProducts.add(productsArr[i]);
				}
				ViewMarketPlace marketMenu = new ViewMarketPlace();
				frame.dispose();
				marketMenu.run(sortedProducts.toArray(new Product[0]));
			}
		});
        
        quantityHtoL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < productsArr.length; i++) {
					for (int j = 1; j < productsArr.length; j++) {
						if (productsArr[j].getQuantityAvailable() > productsArr[i].getQuantityAvailable()) {
							Product temp = productsArr[i];
							productsArr[i] = productsArr[j];
							productsArr[j] = temp;
						}					
					}
				}
				ArrayList<Product> sortedProducts = new ArrayList<Product>();
				for (int i = 0; i < productsArr.length; i++) {
					sortedProducts.add(productsArr[i]);
				}
				ViewMarketPlace marketMenu = new ViewMarketPlace();
				frame.dispose();
				marketMenu.run(sortedProducts.toArray(new Product[0]));
			}
		});
        

        
	}

}
