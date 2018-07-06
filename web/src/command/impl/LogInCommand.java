package command.impl;

import command.ICommand;
import entity.Order;
import entity.User;
import exception.ServiceTechnicalException;
import logic.LogInLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements ICommand {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            LogInLogic logic = new LogInLogic();
            User user = logic.checkLogin(login, password);
            if(user!=null){
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("order", new Order());
                request.getSession().setAttribute("accessDeniedMessage", null);
                response.sendRedirect("blank.jsp");
            } else {
                String errMsg = "Access Denied. Wrong login and/or password.";
                request.getSession().setAttribute("accessDeniedMessage", errMsg);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (ServiceTechnicalException e){
            response.sendRedirect("error.jsp");
        }
    }
}