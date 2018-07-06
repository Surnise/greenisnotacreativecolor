package logic;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class UpdateUserLogic {

    public void updateUser(User user) throws ServiceTechnicalException{
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            userDAO.update(user);
        } catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }

    }
}
