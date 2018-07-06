package command.impl;

import command.ICommand;
import entity.User;
import exception.ServiceTechnicalException;
import logic.ListOrderLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceTechnicalException {
        int customerId = ((User)request.getSession().getAttribute("user")).getId();
        ListOrderLogic logic = new ListOrderLogic();
        request.setAttribute("items", logic.findOrdersForCustomer(String.valueOf(customerId)));
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}
