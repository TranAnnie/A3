package SOA;

import ClientServer.PasswordsAndKeys;
import ClientServer.Shared.Book;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection connection = null;

    public Database() {
        try {
            String dbServerIp = PasswordsAndKeys.dbServerIp;
            String dbServerPort = PasswordsAndKeys.dbServerPort;
            String dbUser = PasswordsAndKeys.dbUsername;
            String dbPassword = PasswordsAndKeys.dbPassword;
            String dbName = PasswordsAndKeys.dbName;
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            if (InetAddress.getLocalHost().getHostName().equals(PasswordsAndKeys.dbHostName)) {
                dbServerIp = "localhost";
            }
            String dbURL = String.format("jdbc:sqlserver://%s:%s;databaseName=" + dbName + ";user=%s;password=%s", dbServerIp, dbServerPort, dbUser, dbPassword);

            connection = DriverManager.getConnection(dbURL);
        } catch(SQLException | UnknownHostException e){
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList();
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
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return bookList;
    }
}
