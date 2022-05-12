package ClientServer.Client;

public class ClientController {

    public ClientController(int nbrOfClients){
        runTest(nbrOfClients);
    }

    private void runTest(int nbrOfClients) {
        long init = System.currentTimeMillis();
        for (int i = 0; i < nbrOfClients; i++) {
            ServerConnection connection = new ServerConnection("127.0.0.1", 6890);
            connection.start();
            try {
                connection.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - init;
        System.out.println("Server-Client. Nbr of clients: "+nbrOfClients +". Time: " +elapsedTime);
    }
}
