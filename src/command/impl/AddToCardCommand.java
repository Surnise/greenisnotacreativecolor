package command.impl;

import command.ICommand;
import entity.Drug;
import entity.Order;
import wrapper.Wrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCardCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object objectOrder = request.getSession().getAttribute("order");
//        AMOUNT???
        Object objectDrug = request.getAttribute("drug");
        int amount = 0;
        try{
            amount = Integer.parseInt(request.getParameter("amount"));
        } catch (NumberFormatException e){
            request.setAttribute("errorMsg", "Invalid parameters");
            request.getRequestDispatcher("currentUrl").forward(request, response);
        }

        if (objectOrder instanceof Order && objectDrug instanceof Drug) {
            Order order = (Order) objectOrder;
            order.addDrug((Drug) objectDrug, amount);
            request.getSession().setAttribute("order", order);//SHOULD I RENEW IT??!!
        } else {
            request.setAttribute("errorCardMsg", "Failed to add drug");
        }
        request.getRequestDispatcher(request.getParameter("currenturl")).forward(request, response);

    }
}
