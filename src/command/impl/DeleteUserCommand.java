package command.impl;

import command.ICommand;
import entity.User;
import exception.ServiceTechnicalException;
import logic.DeleteUserLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceTechnicalException, IOException{
        Object objectUser = request.getAttribute("user");
        if(objectUser instanceof User){
            DeleteUserLogic logic = new DeleteUserLogic();
            logic.deleteAccount((User)objectUser);
        }
        request.setAttribute("errMsg", "Failed to delete account");
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}
