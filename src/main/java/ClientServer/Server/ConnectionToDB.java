package ClientServer.Server;

import ClientServer.PasswordsAndKeys;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {


    private Connection connection;

    public Connection createConnection() throws UnknownHostException, ClassNotFoundException, SQLException {
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

        this.connection = DriverManager.getConnection(dbURL);
        //System.out.println(connection);
        return connection;
    }

    public java.sql.Connection getConnection() {
        if(connection==null) {
            try {
                connection = createConnection();
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (SQLException | ClassNotFoundException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return connection;
    }
}