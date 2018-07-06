package command.impl;

import command.ICommand;
import entity.User;
import exception.ServiceTechnicalException;
import logic.LogInLogic;
import logic.SignUpLogic;
import logic.Validator;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceTechnicalException, ServletException, IOException {
        User user = new User();
        user.setLogin(request.getParameter("login").trim());
        user.setPassword(request.getParameter("password").trim());
        user.setFirstName(request.getParameter("firstName").trim());
        user.setLastName(request.getParameter("lastName").trim());
        user.setEmail(request.getParameter("email").trim());
        Validator validator = new Validator();
        if (validator.isUserValid(user)) {
            LogInLogic logInLogic = new LogInLogic();
            User original = null;
            try {
                original = logInLogic.checkLogin(user.getLogin(), user.getPassword());
            } catch (ServiceTechnicalException e) {
//                LOG IT! DO SMTH!!
            }
            if (original == null) {
                user.setRole("customer");
                SignUpLogic signUpLogic = new SignUpLogic();
                signUpLogic.addAccount(user);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("errorRegMsg", "Such account is already existed");
                request.getRequestDispatcher("currentUrl").forward(request, response);
            }

        } else {
            request.setAttribute("errorRegMsg", "Invalid data");
            request.getRequestDispatcher("currentUrl").forward(request, response);
        }
    }
}
