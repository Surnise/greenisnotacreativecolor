package command.impl;

import command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String local = request.getParameter("local");
        String url = request.getParameter("currenturl");
        request.getSession().setAttribute("local", local);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
