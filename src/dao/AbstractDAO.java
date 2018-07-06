package dao;

import exception.DAOException;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<E> {
    private ConnectionPool connectionPool;

    public AbstractDAO() {
        connectionPool = ConnectionPool.getInstance();

    }
//    public abstract E getById(int id) throws DAOException;
    public abstract List<E> select(String name) throws DAOException;
    public abstract void insert(E entity) throws DAOException;
    public abstract void delete(E entity) throws DAOException;
    public abstract void update(E entity) throws DAOException;

    public void returnConnection(Connection connection){
        if(connection != null){
            connectionPool.returnConnection(connection);
        }
    }

    public Connection getConnection() throws DAOException {
        try {
            return connectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new DAOException(e);
        }
    }
}
