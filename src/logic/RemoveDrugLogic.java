package logic;

import dao.DAOFactory;
import dao.DrugDAO;
import entity.Drug;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class RemoveDrugLogic {

    public void removeDrug(int drugId) throws ServiceTechnicalException{
        DrugDAO drugDAO = DAOFactory.getInstance().getDrugDAO();
        try {
            drugDAO.deleteById(drugId);
        } catch (DAOException e){
            throw new ServiceTechnicalException(e);
        }
    }
}
