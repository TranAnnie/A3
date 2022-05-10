package ClientServer.Server;

public class ServerController {
    private Server server;
    public ServerController (){
        server = new Server(6890);
    }
}
