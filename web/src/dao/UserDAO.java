package dao;

import entity.User;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    private static final String SELECT_BY_LOG_AND_PASS = "SELECT `login`, `password`, `e-mail`, `role` FROM `role` WHERE `login` = ? AND `password` = ?";
    private static final String SELECT_ADVANCED_USER_DATA_FIRST = "SELECT `first_name`, `last_name`, ? FROM ";
    private static final String SELECT_ADVANCED_USER_DATA_SECOND = " WHERE `role_login` = ?";
    private static final String SELECT_ALL_BY_ROLE_FIRST = "SELECT `login`, `password`, `e-mail`, `role`, `first_name`, `last_name`, ? FROM ";
    private static final String SELECT_ALL_BY_ROLE_SECOND = " LEFT JOIN `role` ON ";
    private static final String SELECT_ALL_BY_ROLE_THIRD = ".`role_login` = `role`.`login`";

    private static final String DELETE_USER_FIRST = "DELETE FROM ";
    private static final String DELETE_USER_SECOND = " LEFT JOIN `role` ON ";
    private static final String DELETE_USER_THIRD = ".`role_login` = `role`.`login` WHERE ? = ?";

    private static final String UPDATE_USER_FIRST = "UPDATE ";
    private static final String UPDATE_USER_SECOND = " LEFT JOIN `role` ON ";
    private static final String UPDATE_USER_THIRD = ".`role_login` = `role`.`login` SET `login` = ?, `password` = ?, `e-mail` = ?, `role` = ?, `first_name` = ?, `last_name` = ? WHERE ? = ?";

    private static final String INSERT_USER_FIRST = "INSERT INTO ";
    private static final String INSERT_USER_SECOND = " LEFT JOIN `role` ON ";
    private static final String INSERT_USER_THIRD =".`role_login` = `role`.`login` (`login`, `password`, `e-mail`, `role`, `first_name`, `last_name`) VALUES (?,?,?,?,?,?)";

    //    @Override
    public User getByLoginAndPassword(String login, String password) throws DAOException {
        Connection connection = getConnection();
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOG_AND_PASS);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("e-mail"));
            }
            if (user != null) {
                String query = SELECT_ADVANCED_USER_DATA_FIRST + user.getRole() + SELECT_ADVANCED_USER_DATA_SECOND;
                statement = connection.prepareStatement(query);
                String id = null;
                makeSelectDataStatement(statement, user.getRole());
                statement.setString(2, user.getLogin());
                rs = statement.executeQuery();
                while (rs.next()) {
//            CHECK THIS
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
//                    user.setId(rs.getInt(user.getRole() + "_id")); WTF????
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return user;
    }

    public List<User> select(String role) throws DAOException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        String query = SELECT_ALL_BY_ROLE_FIRST + role + SELECT_ALL_BY_ROLE_SECOND
                + role + SELECT_ALL_BY_ROLE_THIRD;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            makeSelectDataStatement(statement, role);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//            CHECK THIS
                User user = new User();
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("e-mail"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setId(rs.getInt(role + "_id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return users;
    }

    public void update(User user) throws DAOException {
        Connection connection = getConnection();
        String role = user.getRole();
        String update = UPDATE_USER_FIRST + role + UPDATE_USER_SECOND + role + UPDATE_USER_THIRD;

        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getRole() + "_id");
            statement.setInt(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void delete(User user) throws DAOException {
        Connection connection = getConnection();
        String role = user.getRole();
        String delete = DELETE_USER_FIRST + role + DELETE_USER_SECOND + role + DELETE_USER_THIRD;

        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setString(1, user.getRole() + "_id");
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }


    public void insert(User user) throws DAOException {
        Connection connection = getConnection();
        String role = user.getRole();
        String insert = INSERT_USER_FIRST + role + INSERT_USER_SECOND + role + INSERT_USER_THIRD;

        try (PreparedStatement statement = connection.prepareStatement(insert)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    private void makeSelectDataStatement(PreparedStatement statement, String role) throws SQLException, DAOException {
        switch (role.toLowerCase().trim()) {
            case "customer":
                statement.setString(1, "customer_id");
                break;
            case "pharmacist":
                statement.setString(1, "pharmacist_id");
                break;
            case "doctor":
                statement.setString(1, "doctor_id");
                break;
            default:
                throw new DAOException("Invalid role");
        }
    }

}
