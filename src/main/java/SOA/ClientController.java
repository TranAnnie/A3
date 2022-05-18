package SOA;

public class ClientController {

    public static void main(String[] args) {
        int nbrOfClients = 1000;
        ClientGetBooks thread = null;
        long init = System.currentTimeMillis();
        for (int i = 0; i < nbrOfClients; i++) {
            thread = new ClientGetBooks();
            thread.start();
        }
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - init;
        System.out.println("SOA. Nbr of clients: "+nbrOfClients +". Time: " +elapsedTime +" milliseconds");
    }
}
