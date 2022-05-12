package ClientServer.Server;

import ClientServer.Server.Repositories.BookRepository;
import ClientServer.Shared.Book;

import java.sql.Connection;
import java.util.ArrayList;

public class ResponseHandler {

    public ArrayList<Book> handleRequest() {
        Connection connectionToDB = new ConnectionToDB().getConnection();
        BookRepository bookRepository = new BookRepository(connectionToDB);
        return bookRepository.getBooks();
    }
}
