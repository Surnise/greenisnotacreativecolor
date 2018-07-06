package pool;

import exception.ConnectionPoolException;
import manager.ResourceManager;

import java.sql.*;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenConnections;

    private String user;
    private String password;
    private String url;
    private String driver;
    private int poolSize;

    private ConnectionPool() {
        ResourceManager manager = ResourceManager.getInstance();
        user = manager.getValue("db.user");
        password = manager.getValue("db.password");
        url = manager.getValue("db.url");
        driver = manager.getValue("db.driver");
        try {
            poolSize = Integer.parseInt(manager.getValue("db.poolSize"));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
        try{
            initPoolData();
        } catch (ConnectionPoolException e){
//            DO SMTH!!!!
        }

    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    private void initPoolData() throws ConnectionPoolException {

        try {
            Class.forName(driver);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                connectionQueue.add(DriverManager.getConnection(url, user, password));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ConnectionPoolException("Connection pool wasn't initialized", e);
        }
    }

    public Connection getConnection() throws InterruptedException {
        return connectionQueue.take();
    }

    //    THINK ABOUT TITLE
    public void returnConnection(Connection connection) {
        connectionQueue.offer(connection);
    }

    public void dispose() {
        clearConnectionQueue();
    }

    public void closeConnection(Connection connection, Statement st, ResultSet rs) {
        try {
            connection.close();
        } catch (SQLException e) {
//            log it
        }
        try {
            st.close();
        } catch (SQLException e) {
//            log it
        }
        try {
            rs.close();
        } catch (SQLException e) {
//            log it
        }
    }

    public void closeConnection(Connection connection, Statement st) {
        try {
            connection.close();
        } catch (SQLException e) {
//            log it
        }
        try {
            st.close();
        } catch (SQLException e) {
//            log it
        }
    }

    private void clearConnectionQueue() {
        try {
//            closeConnectionQueue(givenAwayConnQueue);
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            //LOG IT!
        }
    }

    private void closeConnectionQueue(BlockingQueue<Connection> connectionQueue) throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();//CHECK IT LATER!!!
        }
    }

}
