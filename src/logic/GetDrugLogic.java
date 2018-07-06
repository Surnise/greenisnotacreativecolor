package logic;

import dao.DAOFactory;
import dao.DrugDAO;
import entity.Drug;
import exception.DAOException;
import exception.ServiceTechnicalException;

public class GetDrugLogic {

    public Drug getDrugByNameAndDose(String name, int dose) throws ServiceTechnicalException{
        DrugDAO drugDAO = DAOFactory.getInstance().getDrugDAO();
        Drug drug;
        try{
            drug = drugDAO.selectByNameAndDose(name, dose);//ПРОВЕРИТЬ РЕЗУЛЬТАТ НА NULL!!!
        }catch (DAOException e) {
            throw new ServiceTechnicalException(e);
        }
        return drug;
    }
}
