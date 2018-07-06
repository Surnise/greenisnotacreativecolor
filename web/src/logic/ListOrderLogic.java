package logic;

import dao.DAOFactory;
import dao.OrderDAO;
import entity.Order;
import exception.DAOException;
import exception.ServiceTechnicalException;

import java.util.List;

public class ListOrderLogic {

    public List<Order> findOrdersForCustomer(String id) throws ServiceTechnicalException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try{
            Integer.parseInt(id);
            return orderDAO.select(id);
        } catch (NumberFormatException | DAOException e) {
            throw new ServiceTechnicalException(e);
        }
    }
}
