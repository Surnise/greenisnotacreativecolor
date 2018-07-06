package command.impl;

import command.ICommand;
import entity.User;
import exception.ServiceTechnicalException;
import logic.UpdateUserLogic;
import logic.Validator;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceTechnicalException, ServletException, IOException {
//        НА СТРАНИЦЕ ПО УМОЛЧАНИЮ В ФОРМУ УЖЕ ВСТАВЛЕНЫ ИМЕЮЩИЕСЯ ЗНАЧЕНИЯ
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setLogin(request.getParameter("login").trim());
        user.setPassword(request.getParameter("password").trim());
        user.setFirstName(request.getParameter("firstName").trim());
        user.setLastName(request.getParameter("lastName").trim());
        user.setEmail(request.getParameter("email").trim());
        Validator validator = new Validator();
        if (validator.isUserValid(user)) {
            UpdateUserLogic updateUserLogic = new UpdateUserLogic();
//            ВСТАВИТЬ ГДЕ_НИБУДЬ ID ЮЗЕРУ (И ДРУГИМ)
            updateUserLogic.updateUser(user);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}