package SOA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;


// The Java class will be hosted at the URI path "/helloworld"
@Path("/products")
public class ProductMService {
    // The Java method will process HTTP GET requests
    @GET
    @Path("/search")
    @Produces("text/plain")
    public String searchForProduct(@QueryParam("type") String type) {
        return "There is no " + type + " available";
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public String getProductsList(){
        ArrayList products = new ArrayList();
        JSONObject product1= new JSONObject();
        product1.put("ID: 1", "Name: MAC");
        products.add(product1);
        JSONObject product2= new JSONObject();
        product2.put("ID: 2", "Type: car");
        products.add(product2);
        JSONObject product3= new JSONObject();
        product3.put("ID: 3", "Type: Laptop");
        products.add(product3);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(products);
        return jsonObject;
    }
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
