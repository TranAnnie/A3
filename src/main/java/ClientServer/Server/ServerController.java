package ClientServer.Server;

import java.sql.Connection;

public class ServerController {
    private Server server;
    private ResponseHandler responseHandler;
    public ServerController (){
        server = new Server(6890, responseHandler);
    }
}
