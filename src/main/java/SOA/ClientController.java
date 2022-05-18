package SOA;

public class ClientController {

    public static void main(String[] args) {
        int nbrOfClients = 50;
        long init = System.currentTimeMillis();
        for (int i = 0; i < nbrOfClients; i++) {
            ClientGetBooks cgb = new ClientGetBooks();
            cgb.start();
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - init;
        System.out.println("SOA. Nbr of clients: "+nbrOfClients +". Time: " +elapsedTime +" milliseconds");
    }
}
