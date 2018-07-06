package command.impl;

import command.ICommand;
import dao.DAOFactory;
import dao.DrugDAO;
import entity.Drug;
import exception.DAOException;
import exception.ServiceTechnicalException;
import logic.AddDrugLogic;
import logic.GetDrugLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDrugCommand implements ICommand {
    @Override
     public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceTechnicalException {
        String name = request.getParameter("name");
        int price = 0;
        int dose = 0;
        boolean prescription = false;
        try {
            dose = Integer.parseInt(request.getParameter("dose"));
            Integer.parseInt(request.getParameter("price"));
            prescription = Integer.parseInt(request.getParameter("prescription")) == 1;
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "Invalid parameters");
            request.getRequestDispatcher("currentUrl").forward(request, response);
        }
                                                                        //WHAT IF PARAMETER IS ABSENT????
        GetDrugLogic getDrugLogic = new GetDrugLogic();
        if(getDrugLogic.getDrugByNameAndDose(name,dose)!=null) {
            Drug drug = new Drug();
            drug.setName(name);
            drug.setPrice(price);
            drug.setDose(dose);
            drug.setPrescriptionNeeded(prescription);


            AddDrugLogic logic = new AddDrugLogic();
            logic.addDrug(drug);
            response.sendRedirect("alteringDrugsPage.jsp");
        }

        request.setAttribute("errorMsg", "This drug is already exists.");
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}
