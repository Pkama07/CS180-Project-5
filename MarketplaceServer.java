import java.net.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * resources:
 * https://www.tutorialspoint.com/java/java_serialization.htm
 * 
 */

public class MarketplaceServer {

    public static ArrayList<Customer> customers;
    public static ArrayList<Store> stores;
    public static ArrayList<Seller> sellers;
    
    public static String[] types = {"customer", "store", "seller"};

    public static void readData(String type) {
        Object o;
        try {
            FileInputStream fIn = new FileInputStream("data/" + type + "s.ser");
            ObjectInputStream in = new ObjectInputStream(fIn);
            o = in.readObject();
            in.close();
            fIn.close();
        } catch (IOException e) {
            System.out.println("There was an error loading the " + type + "s.");
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println(type + " class not found.");
            e.printStackTrace();
            return;
        }
        if (type.equals("seller")) {
            sellers = (ArrayList<Seller>) o;
        }
        else if (type.equals("customer")) {
            customers = (ArrayList<Customer>) o;
        } else if (type.equals("store")) {
            stores = (ArrayList<Store>) o;
        }
    }

    public static void writeData(String type) {
        try {
            FileOutputStream fOut = new FileOutputStream("data/" + type + "s.ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            if (type.equals("seller")) {
                out.writeObject(sellers);
            } else if (type.equals("customer")) {
                out.writeObject(customers);
            } else if (type.equals("store")) {
                out.writeObject(stores);
            }
            fOut.close();
            out.close();
        } catch (IOException e) {
            System.out.println("could not write to " + type + "s.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        boolean running = true;
        for (String type: types) {
            readData(type);
        }
        ServerSocket serverSocket = new ServerSocket(4242);
        while (running) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("client found. starting thread...");
            ClientHandler ct = new ClientHandler(clientSocket);
            ct.start();
            System.out.println("started thread.");
        }
        serverSocket.close();
    }
}