package ClientServer.Server;

import ClientServer.Server.Repositories.BookRepository;
import ClientServer.Shared.Book;

import java.sql.Connection;
import java.util.ArrayList;

public class CreateBooks {

    public static void main(String[] args) {
        //addBooksToDB();
        getBooks();
    }

    private static void getBooks() {
        Connection connection = new ConnectionToDB().getConnection();
        BookRepository bookRepository = new BookRepository(connection);
        ArrayList<Book> books = bookRepository.getBooks();
        for(Book b: books){
            System.out.println(b.getName());
        }
    }

    private static void addBooksToDB() {
        Connection connection = new ConnectionToDB().getConnection();
        BookRepository bookRepository = new BookRepository(connection);
        ArrayList<Book> booksToAdd = generateBooks();
        for(Book book: booksToAdd){
            bookRepository.addBook(book);
        }
    }

    private static ArrayList<Book> generateBooks() {
        ArrayList<Book> booksToAdd = new ArrayList<>();
        for(int i = 1; i <= 50; i++){
            Book book = new Book(i, "Sagan om ringen",  "J.R.R. Tolkien", 1954);
            booksToAdd.add(book);
        }
        return booksToAdd;
    }
}
