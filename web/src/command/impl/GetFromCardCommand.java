package command.impl;

import command.ICommand;
import entity.Drug;
import entity.Order;
import exception.ServiceTechnicalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetFromCardCommand implements ICommand {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceTechnicalException, IOException {
        Order order = (Order)request.getSession().getAttribute("order");
        Drug drug = (Drug)request.getAttribute("drug");
        Map drugs = order.getDrugs();
        if(drugs.containsKey(drug)){
            drugs.remove(drug);
            request.getSession().setAttribute("oredr", order);//SHOULD I RENEW IT???!!!
            request.getRequestDispatcher("currentUrl").forward(request, response);
        } else {
            request.setAttribute("noSuchDrugMsg", "There is no such drug in card");
            request.getRequestDispatcher("currentUrl").forward(request, response);
        }
    }
}
