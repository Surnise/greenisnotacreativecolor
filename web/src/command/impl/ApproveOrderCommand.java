package command.impl;

import command.ICommand;
import dao.DAOFactory;
import dao.OrderDAO;
import entity.Order;
import entity.User;
import exception.ServiceTechnicalException;
import logic.MakeOrderLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApproveOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceTechnicalException {
        Object objectOrder = request.getSession().getAttribute("order");
        if(objectOrder instanceof Order){
            Order order = (Order)objectOrder;
            MakeOrderLogic logic = new MakeOrderLogic();
            logic.makeOrder(order);
            order = new Order();

            Object objectUser = request.getSession().getAttribute("user");
            if(objectUser instanceof User){
                User user = (User)objectUser;
                order.setCustomerId(user.getId());
                request.getSession().setAttribute("order", order);
            }

            response.sendRedirect("index.jsp");

        }
        request.setAttribute("errMsg", "Sudden failure");
        request.getRequestDispatcher("currentUrl").forward(request, response);
    }
}
