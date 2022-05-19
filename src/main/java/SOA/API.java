package SOA;
import ClientServer.Shared.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class API {

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public String getBookList(){
        Database db = new Database();
        List<Book> books = db.getBooks();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonObject = gson.toJson(books);
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
