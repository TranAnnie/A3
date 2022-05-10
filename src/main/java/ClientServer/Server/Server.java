package ClientServer.Server;

import ClientServer.Shared.Book;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private ServerSocket serverSocket = null;
    private ExecutorService executor;
    private boolean isServerRunning;
    private ResponseHandler responseHandler;

    public Server(int port, ResponseHandler responseHandler){
        this.responseHandler = responseHandler;
        executor = Executors.newFixedThreadPool(10);
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
                ClientRequest task = new ClientRequest(socket);
                executor.submit(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    class ClientRequest implements Runnable {

        private ObjectOutputStream oos;
        private Socket socket;

        public ClientRequest(Socket socket) {
            try {
                this.socket = socket;
                this.oos = new ObjectOutputStream(socket.getOutputStream());
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
