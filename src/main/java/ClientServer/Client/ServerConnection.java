package ClientServer.Client;

import ClientServer.Shared.Book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends Thread {
    private String host;
    private int port;
    private ObjectInputStream ois;

    public ServerConnection(String host, int port){
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        ArrayList<Book> response = null;
        try{
            socket = new Socket(host, port);
            ois = new ObjectInputStream(socket.getInputStream());
            response = (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            socket.close();
            ois.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
