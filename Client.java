import java.io.*;
import java.net.*;

import javax.swing.SwingUtilities;

public class Client {
    public static Socket socket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static String userType;
    public static String username;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 4242);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            return;
        }
        SwingUtilities.invokeLater(new Welcome());
    }
}
