package team4.personaldietary.DBManager;

import team4.personaldietary.bean.DbConnectionConfigBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {
    private static LinkedList<Connection> dataSources = new LinkedList<Connection>();
    private static final int POOL_SIZE = 10;

    // static block, when class first loaded, create POOL_SIZE connections, store them in a linked list.
    static {
        DbConnectionPropertiesManager pm = new DbConnectionPropertiesManager();
        DbConnectionConfigBean dcb = new DbConnectionConfigBean();
        String filename = "jarDbConnection"; // properties file

        try {
            dcb = pm.loadTextProperties("",filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(dcb.getFullUrl() + ":" + dcb.getPort() + "/" +
                        dcb.getDatabase(), dcb.getUser(), dcb.getPassword());
                dataSources.add(connection);
                System.out.println("Creating the " + i + "th connection");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a connection from connection pool.
     * @return a connection
     * @throws SQLException Exception
     */
    public Connection getConnection() throws SQLException {
        // get one connection from connection pool.
        System.out.println("Get one connection from pool ");
        return dataSources.removeFirst();
    }

    /**
     * After finish using this connection, release it and return it to connection pool
     * @param connection the connection to be released.
     */
    public void release(Connection connection) {
        System.out.println("Release one connection to pool");
        dataSources.add(connection);
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
