package logic;

import dao.DAOFactory;
import dao.OrderDAO;
import entity.Order;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class DenyOrderLogic {

    public void denyOrder(Order order) throws ServiceTechnicalException{
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            orderDAO.delete(order);
        } catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }
    }
}
