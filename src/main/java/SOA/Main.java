package SOA;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Main {




    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create("http://localhost:9998/");
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
