package ClientServer.Client;

import ClientServer.Shared.RequestMessage;
import ClientServer.Shared.ResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public Connection (String host, int port){
        this.host = host;
        this.port = port;
    }

    public ResponseMessage sendConnection (RequestMessage request){
        ResponseMessage response = null;
        try{
            socket = new Socket(host, port);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
            response = (ResponseMessage) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            socket.close();
            oos.close();
            ois.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
