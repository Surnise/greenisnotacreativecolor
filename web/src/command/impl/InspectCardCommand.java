package command.impl;

import command.ICommand;
import entity.Order;
import entity.User;
import exception.ServiceTechnicalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InspectCardCommand implements ICommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = (Order)request.getSession().getAttribute("order");//CHECK THAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        request.setAttribute("drugs", order.getDrugs());
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}
