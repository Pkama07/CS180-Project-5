import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * ModifyProduct.java
 *
 * Allows the seller to modify attributes of products: name, description, quantity, price and photo
 *
 * @author Adrienne Peters, BLK
 *
 * @version Dec 1, 2022
 *
 */
public class ModifyProduct extends  JComponent implements Runnable{
    TextField newName;
    TextField newDescription;
    TextField newQuantity;
    TextField newPrice;
    JButton changeButton;
    public void run() {
        return;
    }
    public void run(String storeName) {
        //Making frame
        JFrame frame = new JFrame();
        frame.setTitle("180 Market");
        frame.setSize(400 , 200);
        frame.setLocationRelativeTo(null);
        //Ensuring the window closes
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content , BoxLayout.Y_AXIS));
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("storeName", storeName);
        Object[] products;
        try {
            Client.out.writeObject(new Message("getProducts", hm));
            products = (Object[]) Client.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String[] productNames = new String[products.length];
        for (int i = 0; i<products.length; i++) {
            productNames[i] = ((Product) products[i]).getName();
        }
         //Changing image of JOptionPane
        ImageIcon question = new ImageIcon("QuestionMessage.png");
        Image questionImage = question.getImage();
        //Resizing the image
        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
        question = new ImageIcon(resizedQuestion);
        //Asking Seller which product they would like to modify
        String selectedProduct = (String) JOptionPane.showInputDialog(null ,
                "Which product would you like to modify?" , "180-Market" , JOptionPane.QUESTION_MESSAGE ,
                question , productNames , productNames[0]);
        if ( selectedProduct == null || selectedProduct.equals("")) {
            frame.dispose();
            StoreOptions storeOptionsMenu = new StoreOptions();
            storeOptionsMenu.run(storeName);
        }
        String[] attributes = {"Name" , "Description" , "Quantity" , "Price" , "Photo"};
        //Asking Seller which attribute they would like to change
        String attributeSelected = (String) JOptionPane.showInputDialog(null ,
                "What would you like to modify", "180-Market" , JOptionPane.QUESTION_MESSAGE , question ,
                attributes , attributes[0] );
        if ( attributeSelected == null ) {
            frame.dispose();
            StoreOptions storeOptionsMenu = new StoreOptions();
            storeOptionsMenu.run(storeName);
        }
        switch (attributeSelected ) {
            case "Name" : //If the Seller picked to edit the product's name
                //Creating and formatting buttons/textFields
                newName = new TextField("New Name");
                content.add(newName);
                Panel buttonPanel = new Panel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.X_AXIS));
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                buttonPanel.add(changeButton);
                content.add(buttonPanel);
                frame.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        //Changing image of JOptionPane
                        ImageIcon question = new ImageIcon("QuestionMessage.png");
                        Image questionImage = question.getImage();
                        //Resizing the image
                        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 ,
                                Image.SCALE_SMOOTH);
                        question = new ImageIcon(resizedQuestion);
                        //Asking Seller to confirm
                        int confirm = JOptionPane.showConfirmDialog(frame , "Confirm you would like to change "
                                + selectedProduct + "'s name to " + newName.getText() ,
                                "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE ,
                                question);
                        if ( confirm == JOptionPane.NO_OPTION ) { //If the Seller doesn't confirm
                            //Close the frame, call run() again and return
                            frame.dispose();
                            run(storeName);
                            return;
                        } else if ( confirm == JOptionPane.YES_OPTION ) { //If the Seller confirms
                            //Change the name
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("storeName", storeName);
                            m.put("productName", selectedProduct);
                            m.put("changed", "name");
                            m.put("newVal", newName.getText());
                            try {
                                Client.out.writeObject(new Message("modifyProduct", m));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        } else if ( confirm == JOptionPane.CLOSED_OPTION ) { //If window is closed
                            //Close the frame and return
                            frame.dispose();
                            return;
                        }
                        frame.dispose();
                        StoreOptions storeOptionsMenu = new StoreOptions();
                        storeOptionsMenu.run(storeName);
                    }
                });
                break;
            case "Description" : //If the Seller picked to edit the product's description
                //Creating and formatting buttons/textFields
                buttonPanel = new Panel();
                newDescription = new TextField("New Description");
                content.add(newDescription);
                buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.X_AXIS));
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                buttonPanel.add(changeButton);
                content.add(buttonPanel);
                frame.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        //Changing image of JOptionPane
                        ImageIcon question = new ImageIcon("QuestionMessage.png");
                        Image questionImage = question.getImage();
                        //Resizing the image
                        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 ,
                                Image.SCALE_SMOOTH);
                        question = new ImageIcon(resizedQuestion);
                        int confirm = JOptionPane.showConfirmDialog(frame , "Confirm you would like to change "
                                        + selectedProduct + "'s description to " + newDescription.getText() ,
                                "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE ,
                                question);
                        if ( confirm == JOptionPane.NO_OPTION ) {
                            //Close the frame, call run() again and return
                            frame.dispose();
                            run(storeName);
                            return;
                        } else if ( confirm == JOptionPane.CLOSED_OPTION ) {
                            //Close the frame and return
                            frame.dispose();
                            return;
                        } else if ( confirm == JOptionPane.YES_OPTION) {
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("storeName", storeName);
                            m.put("productName", selectedProduct);
                            m.put("changed", "description");
                            m.put("newVal", newDescription.getText());
                            try {
                                Client.out.writeObject(new Message("modifyProduct", m));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                        frame.dispose();
                        StoreOptions storeOptionsMenu = new StoreOptions();
                        storeOptionsMenu.run(storeName);
                    }
                });
                break;
            case "Quantity" : //If the Seller picked to edit the product's quantity
                //Creating and formatting buttons/textFields
                buttonPanel = new Panel();
                newQuantity = new TextField("New Quantity");
                content.add(newQuantity);
                buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.X_AXIS));
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                buttonPanel.add(changeButton);
                content.add(buttonPanel);
                frame.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {  //If the user clicks the changeButton
                        //Changing image of JOptionPane
                        ImageIcon question = new ImageIcon("QuestionMessage.png");
                        Image questionImage = question.getImage();
                        //Resizing the image
                        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 ,
                                Image.SCALE_SMOOTH);
                        question = new ImageIcon(resizedQuestion);
                        int confirm = JOptionPane.showConfirmDialog(frame , "Confirm you would like to change "
                                        + selectedProduct + "'s quantity to " + newQuantity.getText() ,
                                "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE ,
                                question);
                        if ( confirm == JOptionPane.NO_OPTION ) {
                            //Close the frame, call run(storeName) again and return
                            frame.dispose();
                            run(storeName);
                            return;
                        } else if ( confirm == JOptionPane.CLOSED_OPTION ) {
                            //Close the frame and return
                            frame.dispose();
                            return;
                        } else if ( confirm == JOptionPane.YES_OPTION ) {
                            if (!newQuantity.getText().matches("[0-9]+")) {
                                //Changing image of JOptionPane
                                ImageIcon warning = new ImageIcon("WarningImage.png");
                                Image warningImage = warning.getImage();
                                //Resizing the image
                                Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                                warning = new ImageIcon(resizedWarning);
                                frame.dispose();
                                int closed = JOptionPane.showConfirmDialog(null,
                                        "Please enter a number in the New quantity field", "180-Market",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE , warning);
                                if ( closed == JOptionPane.CANCEL_OPTION ) {
                                    ManageStore manageStoreMenu = new ManageStore();
                                    frame.dispose();
                                    manageStoreMenu.run();
                                }
                                if ( closed == JOptionPane.CLOSED_OPTION ) {
                                    return;
                                }
                                if ( closed == JOptionPane.OK_OPTION ) {
                                    frame.dispose();
                                    run(storeName);
                                }
                            }
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("storeName", storeName);
                            m.put("productName", selectedProduct);
                            m.put("changed", "quantity");
                            m.put("newVal", newQuantity.getText());
                            try {
                                Client.out.writeObject(new Message("modifyProduct", m));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                        frame.dispose();
                        StoreOptions storeOptionsMenu = new StoreOptions();
                        storeOptionsMenu.run(storeName);
                    }
                });
                break;
            case "Price" : //If the Seller picked to edit the product's price
                //Creating and formatting buttons/textFields
                buttonPanel = new Panel();
                newPrice = new TextField("New Price");
                content.add(newPrice);
                buttonPanel.setLayout(new BoxLayout(buttonPanel , BoxLayout.X_AXIS));
                changeButton = new JButton("Change");
                changeButton.setAlignmentX(CENTER_ALIGNMENT);
                buttonPanel.add(changeButton);
                content.add(buttonPanel);
                frame.setVisible(true);
                changeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { //If the user clicks the changeButton
                        //Changing image of JOptionPane
                        ImageIcon question = new ImageIcon("QuestionMessage.png");
                        Image questionImage = question.getImage();
                        //Resizing the image
                        Image resizedQuestion = questionImage.getScaledInstance(100 , 100 ,
                                Image.SCALE_SMOOTH);
                        question = new ImageIcon(resizedQuestion);
                        int confirm = JOptionPane.showConfirmDialog(frame , "Confirm you would like to change "
                                        + selectedProduct + "'s Price to " + newPrice.getText() ,
                                "180-Market" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE ,
                                question);
                        if ( confirm == JOptionPane.NO_OPTION ) {
                            //Close the frame, call run(storeName) again and return
                            frame.dispose();
                            run(storeName);
                            return;
                        } else if ( confirm == JOptionPane.CLOSED_OPTION ) {
                            //Close the frame and return
                            frame.dispose();
                            return;
                        } else if ( confirm == JOptionPane.YES_OPTION ) {
                            if (!newPrice.getText().matches("[0-9.0-9]+")) {
                                //Changing image of JOptionPane
                                ImageIcon warning = new ImageIcon("WarningImage.png");
                                Image warningImage = warning.getImage();
                                //Resizing the image
                                Image resizedWarning = warningImage.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                                warning = new ImageIcon(resizedWarning);
                                frame.dispose();
                                int closed = JOptionPane.showConfirmDialog(null,
                                        "Please enter a number in the New quantity field", "180-Market",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE , warning);
                                if ( closed == JOptionPane.CANCEL_OPTION ) {
                                    ManageStore manageStoreMenu = new ManageStore();
                                    frame.dispose();
                                    manageStoreMenu.run();
                                }
                                if ( closed == JOptionPane.CLOSED_OPTION ) {
                                    return;
                                }
                                if ( closed == JOptionPane.OK_OPTION ) {
                                    frame.dispose();
                                    run(storeName);
                                }
                            }
                            HashMap<String, String> m = new HashMap<String, String>();
                            m.put("storeName", storeName);
                            m.put("productName", selectedProduct);
                            m.put("changed", "price");
                            m.put("newVal", newPrice.getText());
                            try {
                                Client.out.writeObject(new Message("modifyProduct", m));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                        frame.dispose();
                        StoreOptions storeOptionsMenu = new StoreOptions();
                        storeOptionsMenu.run(storeName);
                    }
                });
                break;
        }
        //System.out.println(selectedProduct.getName());
        /*
        //Getting what product they want to mofify
        int index = Integer.parseInt(s.nextLine());
        boolean modifying = true;
        boolean satisfied = true;
        do {
            //Asking what attribute they want to change
            System.out.println("What would you like to modify\n0.Name\n1.Description\n2.Quantity\n3.Price");
            int choice = Integer.parseInt(s.nextLine());
            int i = products2.indexOf(products.get(index));
            String input = "";
            switch (choice) {
                case 0://Modifying name
                    do {
                        //Asking for new name
                        System.out.println("Current name : " + products.get(index).getName() +
                                "\nWhat would you like the new name to be?");
                        input = s.nextLine();
                        //Setting name to the new name
                        products.get(index).setName(input);
                        products2.get(i).setName(input);
                        System.out.println("This is the new name:\n" + products.get(index).getName());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new name
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 1://Modifying description
                    do {
                        //Asking for new description
                        System.out.println("Current description: " + products.get(index).getDescription() +
                                "\nWhat would you like the new description to be?");
                        input = s.nextLine();
                        //Setting description to new one
                        products.get(index).setDescription(input);
                        products2.get(i).setDescription(input);
                        System.out.println("This is the new description:\n" + products.get(index).getDescription());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new description
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 2://Modifying quantity
                    do {
                        //Asking for new quantity
                        System.out.println("Current quantity: " + products.get(index).getQuantityAvailable() +
                                "\nWhat would you like the new quantity to be?");
                        int iInput = Integer.parseInt(s.nextLine());
                        //Setting quantity to new one
                        products.get(index).setQuantityAvailable(iInput);
                        products2.get(i).setQuantityAvailable(iInput);
                        System.out.println("This is the new quantity:\n" + products.get(index).getQuantityAvailable());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new quantity
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                case 3://Modifying price
                    do {
                        //Asking for new price
                        System.out.printf("Current price: %.2f \nWhat would you like the new price to be?\n",
                                products.get(index).getPrice());
                        double dInput = Double.parseDouble(s.nextLine());
                        //Setting price to a new one
                        products.get(index).setPrice(dInput);
                        products2.get(i).setPrice(dInput);
                        System.out.printf("This is the new price:\n%.2f\n", products.get(index).getPrice());
                        System.out.println("Satisfied?\ny/n: ");
                        input = s.nextLine();
                        //If the seller is satisfied the loop ends, if not the seller can continue
                        //modifying their product until they are satisfied with the new price
                        if (input.equals("y")) {
                            modifying = false;
                            satisfied = false;
                        }
                    } while (satisfied);
                    break;
                default:
                    //If the Store doesn't enter a number 1-3
                    System.out.println("Please enter a valid number");
                    break;
            }
        } while (modifying);*/
    }
}
