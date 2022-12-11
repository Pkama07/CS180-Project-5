import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

/**
 * StoreOptions.java
 *
 * After the Seller picks what store they want to manage they are taken to this class where they pick what they would
 * like to do with their store: create a product, modify a product, delete a product, and import and export products
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class StoreOptions extends JComponent implements Runnable {
    JButton createProductButton;
    JButton modifyProductButton;
    JButton deleteProductButton;
    JButton importProductsButton;
    JButton exportProductsButton;
    JButton backButton;
    public void run(){
        return;
    }
    public void run(String storeName) {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring the frame closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        //Creating and formatting buttons
        createProductButton = new JButton("Create Product");
        createProductButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(createProductButton);
        modifyProductButton = new JButton("Modify Product");
        modifyProductButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(modifyProductButton);
        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(deleteProductButton);
        importProductsButton = new JButton("Import Product");
        importProductsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(importProductsButton);
        exportProductsButton = new JButton("Export Product");
        exportProductsButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(exportProductsButton);
        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        content.add(backButton);
        frame.setVisible(true);
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // If the Seller clicks on the createProductButton
                CreateProduct createProductMenu = new CreateProduct();
                //Close the frame and call CreateProduct's run method
                frame.dispose();
                createProductMenu.run(storeName);
            }
        });
        modifyProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // If the Seller clicks on the modifyProductButton
                ModifyProduct modifyProductMenu = new ModifyProduct();
                //Close the frame and call ModifyProduct's run method
                frame.dispose();
                modifyProductMenu.run(storeName);
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // If the Seller clicks on the deleteProductButton
                DeleteProduct deleteProductMenu = new DeleteProduct();
                //Close the frame and call DeleteProduct's run method
                frame.dispose();
                deleteProductMenu.run(storeName);
            }
        });
        importProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {// If the Seller clicks on the importProductButton
                ImportProducts importProductsMenu = new ImportProducts();
                //Close the frame and call ImportProduct's run method
                frame.dispose();
                importProductsMenu.run(storeName);
            }
        });
        exportProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {// If the Seller clicks on the exportProductButton
                ExportProducts exportProductsMenu = new ExportProducts();
                //Close the frame and call ExportProduct's run method
                frame.dispose();
                exportProductsMenu.run(storeName);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // If the Seller clicks on the backButton
                StoreMenu storeMenu = new StoreMenu();
                //Close the frame and call StoreMenu's run method
                frame.dispose();
                storeMenu.run();
            }
        });
    }
}
