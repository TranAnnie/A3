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

    public Server(int port){
        this.responseHandler = new ResponseHandler();
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
            isServerRunning = true;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(isServerRunning){
            try {
                Socket socket = serverSocket.accept();
                new ClientRequest(socket).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Server is closed");
    }

    class ClientRequest implements Runnable {

        private ObjectOutputStream oos;
        private Socket socket;

        public ClientRequest(Socket socket) {
            try {
                this.socket = socket;
                this.oos = new ObjectOutputStream(socket.getOutputStream());
                run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
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
    }
}
