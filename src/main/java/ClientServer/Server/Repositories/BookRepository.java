package ClientServer.Server.Repositories;

import ClientServer.Server.ConnectionToDB;
import ClientServer.Shared.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookRepository {

    Connection connection;

    public BookRepository(Connection connection) {
       this.connection = connection;
    }

    public boolean addBook(Book book) {
        String query = "INSERT INTO [Books] VALUES ('" + book.getStoreId() + "', '" + book.getName() + "', '" + book.getFirstAuthor() + "'," + book.getPublishingYear() + ");";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM [Books];";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("StoreId");
                String name = resultSet.getString("Name");
                String firstAuthor = resultSet.getString("FirstAuthor");
                int publishingYear = resultSet.getInt("PublishingYear");
                bookList.add(new Book(id, name, firstAuthor, publishingYear));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return bookList;
    }
}

