package ClientServer.Server;

import ClientServer.Shared.Book;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private ServerSocket serverSocket = null;
    private boolean isServerRunning;
    private ResponseHandler responseHandler;
    private Socket socket;
    private ObjectOutputStream oos;

    public Server(int port) {
        this.responseHandler = new ResponseHandler();
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
            isServerRunning = true;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isServerRunning) {
            try {
                socket = serverSocket.accept();
                this.oos = new ObjectOutputStream(socket.getOutputStream());
                ArrayList<Book> books = responseHandler.handleRequest();
                oos.writeObject(books);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                    socket.close();
                } catch (IOException ie) {
                    System.out.println("Socket Close Error");
                }
            }
        }
        System.out.println("Server is closed");
    }
}