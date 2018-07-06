package dao;

import entity.Drug;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO extends AbstractDAO<Drug> {

    private static final String SELECT_ALL_SIMILAR = "SELECT `drug_id`, `name`, `dose`, `prescription`, `price` FROM drug WHERE `name` LIKE %?%";
    private static final String SELECT_BY_NAME_AND_DOSE = "SELECT `drug_id`, `name`, `dose`, `prescription`, `price` FROM drug WHERE `name` = ?, `dose` = ?";
    private static final String UPDATE_DRUG = "UPDATE `drug` SET `name` = ?, `dose` = ?, `prescription` = ? , `price` = ? WHERE `drug_id` = ?";
    private static final String DELETE_DRUG = "DELETE FROM `drug` WHERE `drug_id` = ?";
    private static final String INSERT_DRUG = "INSERT INTO `drug` (`name`, `dose`, `prescription`, `price`) VALUES (?,?,?,?);";

    public List<Drug> select(String name) throws DAOException {
        List<Drug> drugs = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SIMILAR);) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//            CHECK THIS
                Drug drug = new Drug();
                drug.setId(rs.getInt("drug_id"));
                drug.setDose(rs.getInt("dose"));
                drug.setName(rs.getString("name"));
                drug.setPrescriptionNeeded(rs.getInt("prescription") == 1);
                drugs.add(drug);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return drugs;
    }

    public void update(Drug drug) throws DAOException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_DRUG);) {
            statement.setString(1, drug.getName());
            statement.setInt(2, drug.getDose());
            statement.setInt(3, drug.isPrescriptionNeeded() ? 1 : 0);
            statement.setInt(4, drug.getPrice());
            statement.setInt(5, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void delete(Drug drug) throws DAOException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DRUG);) {
            statement.setInt(1, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void deleteById(int id) throws DAOException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_DRUG);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public void insert(Drug drug) throws DAOException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_DRUG);) {
            statement.setString(1, drug.getName());
            statement.setInt(2, drug.getDose());
            statement.setInt(3, drug.isPrescriptionNeeded() ? 1 : 0);
            statement.setInt(4, drug.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }

    public Drug selectByNameAndDose(String name, int dose) throws DAOException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME_AND_DOSE)) {
            statement.setString(1, name);
            statement.setInt(2, dose);
            ResultSet rs = statement.executeQuery();
            Drug drug = rs.next() ? new Drug() : null;

            if (drug != null) {
                drug.setId(rs.getInt("drug_id"));
                drug.setDose(rs.getInt("dose"));
                drug.setName(rs.getString("name"));
                drug.setPrescriptionNeeded(rs.getInt("prescription") == 1);
            }
            return drug;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
    }
}
