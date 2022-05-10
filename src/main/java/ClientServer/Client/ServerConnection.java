package ClientServer.Client;

import ClientServer.Shared.ResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnection extends Thread {
    private String host;
    private int port;
    private Socket socket;
    private ObjectInputStream ois;

    public ServerConnection(String host, int port){
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        ResponseMessage response = null;
        try{
            socket = new Socket(host, port);

            ois = new ObjectInputStream(socket.getInputStream());
            response = (ResponseMessage) ois.readObject();
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
