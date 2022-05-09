package ClientServer.Server;

import java.sql.Connection;

public class ServerController {
    private Server server;
    private ResponseHandler responseHandler;
    public ServerController (){
        Connection connection = new ConnectionToDB().getConnection();
        System.out.println(connection);
        responseHandler = new ResponseHandler();
        server = new Server(6890, responseHandler);
    }
}
