package logic;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class SignUpLogic {

    public void addAccount(User user) throws ServiceTechnicalException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            userDAO.insert(user);
        } catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }
    }
}
