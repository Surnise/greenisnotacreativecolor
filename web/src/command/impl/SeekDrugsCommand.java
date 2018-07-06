package command.impl;

import command.ICommand;
import entity.Drug;
import exception.ServiceTechnicalException;
import logic.SeekDrugsLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SeekDrugsCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceTechnicalException {
        String name = request.getParameter("name");
        List<Drug> drugs = new SeekDrugsLogic().searchDrug(name);
        request.setAttribute("drugs", drugs);
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}