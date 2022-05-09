package ClientServer.Client;

public class ClientController {
    private RequestHandler requestHandler;

    public ClientController(){
        this.requestHandler = new RequestHandler("127.0.0.1", 6890);
    }

    //Gör metoder för att göra saker som behövs göras!
}
