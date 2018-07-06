package logic;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import exception.DAOException;
import exception.ServiceTechnicalException;
import wrapper.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class LogInLogic {

    public User checkLogin(String login, String password) throws ServiceTechnicalException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try{
            return userDAO.getByLoginAndPassword(login, password);
        }catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }

    }
}
