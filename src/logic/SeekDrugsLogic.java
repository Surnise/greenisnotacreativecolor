package logic;

import dao.DAOFactory;
import dao.DrugDAO;
import entity.Drug;
import exception.DAOException;
import exception.ServiceTechnicalException;

import java.util.List;

public class SeekDrugsLogic {

    public List<Drug> searchDrug(String name)throws ServiceTechnicalException {
        DrugDAO drugDAO = DAOFactory.getInstance().getDrugDAO();
        try {
            return drugDAO.select(name);
        } catch (DAOException e ) {
            throw new ServiceTechnicalException(e);
        }
    }
}
