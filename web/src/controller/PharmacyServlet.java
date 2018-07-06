package controller;

import command.CommandContainer;
import command.CommandFactory;
import command.ICommand;
import exception.ServiceTechnicalException;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class PharmacyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int y = Integer.parseInt(request.getSessionAttribute("x")) * 2;
//        response.getOutputStream().print(y);
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        CommandFactory factory = new CommandFactory();//static???
        CommandContainer container = CommandContainer.getInstance();
        ICommand command = container.getCommand(request.getParameter("command"));
        try {
            command.execute(request, response);
        } catch (ServiceTechnicalException e){
            response.sendRedirect("error.jsp");
        }
//        String url = null;
//        Wrapper wrapper = new Wrapper();
//        wrapper.extract(request);
//        CommandFactory factory = new CommandFactory()
//        ICommand command = factory.getExecutor(request.getRequestAttribute("command"));
//        CHECK IF COMMAND == NULL!!
//        url = command.execute(wrapper);
//        wrapper.updateRequest(request);
//        CHECK IF URL == NULL!!
//        request.getRequestDispatcher(url).forward(request, response);
    }

}
