package controllers;

import dispatcher.resolver.types.JsonResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CustomerDTO;
import services.CartService;
import services.InsufficientCreditException;
import services.OrderService;
import services.OutOfStockException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("customer") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            new JsonResolver().render("You are not logged in", req, resp);
        }
        else{
            try{
            CustomerDTO customerDTO=(CustomerDTO) req.getSession().getAttribute("customer");
            Map<String,Object> map = new OrderService().checkout(customerDTO.id());
            new JsonResolver().render(map, req, resp);}
            catch(OutOfStockException|InsufficientCreditException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> response = new HashMap<>();

                if (e instanceof InsufficientCreditException) {
                    response.put("creditAvailable", ((InsufficientCreditException) e).getCreditLimit());
                    response.put("type", "InsufficientCreditError");
                }
                else if(e instanceof OutOfStockException) {
                    response.put("outOfStockProducts", ((OutOfStockException) e).getOutOfStockProducts());
                    response.put("type", "OutOfStockError");
                }
                new JsonResolver().render(response, req, resp);
            }

        }

    }

}