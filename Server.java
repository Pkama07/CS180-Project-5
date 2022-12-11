import java.net.*;

/*
 * resources:
 * https://stackoverflow.com/questions/13105122/loop-in-java-until-the-users-pushes-enter
 * 
 */

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4242);
        boolean running = true;
        while (running) {
            System.out.println("waiting for new client to connect...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("connection found. creating new thread for client...");
            ClientHandler ct = new ClientHandler(clientSocket);
            ct.start();
            System.out.println("thread created.");
        }
        serverSocket.close();
    }
}