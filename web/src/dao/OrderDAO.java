package dao;

import entity.Drug;
import entity.Order;
import exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDAO extends AbstractDAO<Order> {

    private static final String SELECT_ORDERS_OF_CUSTOMER = "SELECT `order_id`," +
            " `order_date`, `customer_id`, `pharmacist_id`, `name`, `dose`, " +
            "`prescription`, `price`, `drug_id` `amount` FROM `order_m2m_drug`" +
            " LEFT JOIN `order` USING (`order_id`) LEFT JOIN `drug` USING (`drug_id`) " +
            "WHERE `customer_id` = ? ORDER BY `order_id`";
    private static final String DELETE_ORDERS = "DELETE FROM `order_m2m_drug` " +
            "LEFT JOIN `order` USING (`order_id`) LEFT JOIN `drug` USING (`drug_id`)" +
            " WHERE `order_id` = ?";
    private static final String INSERT_ORDER = "INSERT INTO `order_m2m_drug` LEFT JOIN" +
            " `order` USING (`order_id`) LEFT JOIN `drug` USING (`drug_id`) (`order_id`," +
            " `order_date`, `customer_id`, `pharmacist_id`, `name`, `dose`," +
            " `prescription`, `price`, `drug_id`, `amount`) VALUES (?,?,?,?,?,?,?,?,?,?)";

    public List<Order> select(String customerId) throws DAOException{
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ORDERS_OF_CUSTOMER);
            statement.setString(1, customerId);
            ResultSet rs = statement.executeQuery();
            int orderId = 0;
            Order order = null;//CHECK IF NULL!!!

            while (rs.next()) {
                if(rs.getInt("order_id") != orderId){
                    orderId = rs.getInt("order_id");
                    order = new Order();
                    order.setId(orderId);
                    order.setOrderDate(rs.getDate("order_date").toLocalDate());
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setPharmacistId(rs.getInt("pharmacist_id"));

                    Drug drug = new Drug();
                    drug.setId(rs.getInt("drug_id"));
                    drug.setDose(rs.getInt("dose"));
                    drug.setName(rs.getString("name"));
                    drug.setPrescriptionNeeded(rs.getInt("prescription") == 1);
                    order.addDrug(drug, rs.getInt("amount"));
                } else {
                    if (order != null) {
                        Drug drug = new Drug();
                        drug.setId(rs.getInt("drug_id"));
                        drug.setDose(rs.getInt("dose"));
                        drug.setName(rs.getString("name"));
                        drug.setPrescriptionNeeded(rs.getInt("prescription") == 1);
                        order.addDrug(drug, rs.getInt("amount"));
                    }
//            CHECK THIS
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return orders;
    }

//    НЕ ЗАПИЛЕНО!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void update(Order order) throws DAOException{
        Connection connection = getConnection();;
        try {
            String query = "UPDATE `drug` SET `name` = ?, `dose` = ?, `prescription` = ? , `price` = ? WHERE `id` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, drug.getName());
//            statement.setInt(2, drug.getDose());
//            statement.setInt(3, drug.isPrescriptionNeeded() ? 1 : 0);
//            statement.setInt(4, drug.getPrice());
//            statement.setInt(5, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void delete(Order order) throws DAOException{
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDERS)){

//            String query = "DELETE FROM `drug` WHERE `name` = ?, `dose` = ?, `prescription` = ?, `price` = ?";
            statement.setInt(1, order.getId());
//            statement.setString(1, drug.getName());
//            statement.setInt(2, drug.getDose());
//            statement.setInt(3, drug.isPrescriptionNeeded() ? 1 : 0);
//            statement.setInt(4, drug.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
//            FIX IT
        } finally {
            returnConnection(connection);
        }
    }

//    public void deleteById(int id) {
//        try {
//            Connection connection = getConnection();
//            String query = "DELETE FROM `drug` WHERE `id` = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
////            FIX IT
//        } catch (DAOException e) {
////            FIX IT
//        }
//    }

    public void insert(Order order) throws DAOException{
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER);){
            statement.setInt(1, order.getId());
            statement.setDate(2, Date.valueOf(order.getOrderDate()));
            statement.setInt(3, order.getCustomerId());
            statement.setInt(4, order.getPharmacistId());

            Map<Drug, Integer> drugs = order.getDrugs();
            for(Drug drug: drugs.keySet()){
                statement.setString(5, drug.getName());
                statement.setInt(6, drug.getDose());
                statement.setInt(7, drug.isPrescriptionNeeded() ? 1 : 0);
                statement.setInt(8, drug.getPrice());
                statement.setInt(9, drug.getId());
                statement.setInt(10, drugs.get(drug));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }
}
