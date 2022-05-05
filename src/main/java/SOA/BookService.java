package SOA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

@Path("/books")
public class BookService {

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public String searchBooksByTitle(@QueryParam("title") String title){
        return null;
    }

    @GET
    @Path("/allBooks")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBooks(){
        ArrayList books = new ArrayList();
        JSONObject book1 = new JSONObject(); //get all books from DB
        book1.put("ID: 1", "Name: Hello world");
        books.add(book1);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String jsonObject = gson.toJson(books);
        return jsonObject;

    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9989/");
        server.start();

        System.out.println("Server running");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}
