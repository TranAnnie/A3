package ClientServer.Client;

public class ClientController {

    public ClientController(int nbrOfClients) {
        runTest(nbrOfClients);
    }

    private void runTest(int nbrOfClients) {
        ServerConnection thread = null;
        long init = System.currentTimeMillis();
        for (int i = 0; i < nbrOfClients; i++) {
            thread = new ServerConnection("127.0.0.1", 6890);
            thread.start();
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - init;
        System.out.println("Server-Client. Nbr of clients: " + nbrOfClients + ". Time: " + elapsedTime + " milliseconds");
    }
}
