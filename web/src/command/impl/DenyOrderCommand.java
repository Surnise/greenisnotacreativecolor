package command.impl;

import command.ICommand;
import dao.DAOFactory;
import dao.OrderDAO;
import entity.Order;
import entity.User;
import exception.ServiceTechnicalException;
import logic.DenyOrderLogic;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DenyOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceTechnicalException, IOException {
        Object objectOrder = request.getAttribute("order");
        if(objectOrder instanceof Order){
            DenyOrderLogic logic = new DenyOrderLogic();
            logic.denyOrder((Order)objectOrder);
        }
        response.sendRedirect("currentUrl");//НА ТУ ЖЕ СТРАНИЦУ С ЗАКАЗАМИ
    }
}
