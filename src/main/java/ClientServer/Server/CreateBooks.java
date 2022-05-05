package ClientServer.Server;

import main.java.ClientServer.Server.Repositories.BookRepository;
import main.java.ClientServer.Shared.Book;

import java.sql.Connection;
import java.util.ArrayList;

public class CreateBooks {

    public static void main(String[] args) {
        Connection connection = new ConnectionToDB().getConnection();
        BookRepository bookRepository = new BookRepository(connection);
        ArrayList<Book> booksToAdd = generateBooks();
        for(Book book: booksToAdd){
            bookRepository.addBook(book);
        }
    }

    private static ArrayList<Book> generateBooks() {
        ArrayList<Book> booksToAdd = null;
        for(int i = 1; i <= 50; i++){
            Book book = new Book(i, "Sagan om ringen",  "J.R.R. Tolkien", 1954);
            booksToAdd.add(book);
        }
        return booksToAdd;
    }
}
