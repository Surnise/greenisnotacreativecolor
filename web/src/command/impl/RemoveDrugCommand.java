package command.impl;

import command.ICommand;
import exception.DAOException;
import exception.ServiceTechnicalException;
import logic.RemoveDrugLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveDrugCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceTechnicalException, ServletException, IOException {
        try{
            int drugId = Integer.parseInt(request.getParameter("drugid"));
            RemoveDrugLogic logic = new RemoveDrugLogic();
            logic.removeDrug(drugId);
            response.sendRedirect("currenturl");
        } catch (NumberFormatException e){
            request.setAttribute("illegalDrugId", "Drug wasn't removed because of wrong id");
            request.getRequestDispatcher("currenturl").forward(request,response);
        }

    }
}