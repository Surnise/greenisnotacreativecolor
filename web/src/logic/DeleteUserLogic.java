package logic;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class DeleteUserLogic {

    public void deleteAccount(User user) throws ServiceTechnicalException{
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            userDAO.delete(user);
        } catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }
    }
}
