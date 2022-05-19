package ClientServer.Server;

import ClientServer.Shared.BookList;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ResponseHandler responseHandler;

    public Server(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        this.responseHandler = new ResponseHandler();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ClientRequest(socket).start();
        }
    }

    class ClientRequest extends Thread {

        protected Socket socket;
        private ObjectOutputStream oos;

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
                BookList bookList = new BookList(responseHandler.handleRequest());
                oos.writeObject(bookList);
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
