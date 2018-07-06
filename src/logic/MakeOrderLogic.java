package logic;

import dao.DAOFactory;
import dao.OrderDAO;
import entity.Order;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class MakeOrderLogic {

    public void makeOrder(Order order) throws ServiceTechnicalException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            orderDAO.insert(order);
        }catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }
    }

}
