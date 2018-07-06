package logic;

import dao.DAOFactory;
import dao.DrugDAO;
import entity.Drug;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class AddDrugLogic {

    public void addDrug(Drug drug) throws ServiceTechnicalException{
        DrugDAO drugDAO = DAOFactory.getInstance().getDrugDAO();
        try {
            drugDAO.insert(drug);
        } catch (DAOException e) {
            throw new ServiceTechnicalException(e);
        }
    }
}
