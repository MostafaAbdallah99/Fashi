package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.dto.CustomerDTO;
import exceptions.InsufficientCreditException;
import services.impl.OrderService;
import exceptions.OutOfStockException;
import utils.JsonResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("customer") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            JsonResolver.render("You are not logged in", resp);
        } else {
            try {
                CustomerDTO customerDTO = (CustomerDTO) req.getSession().getAttribute("customer");
                Map<String, Object> map = new OrderService().checkout(customerDTO.id());
                JsonResolver.render(map, resp);
            } catch (OutOfStockException | InsufficientCreditException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> response = new HashMap<>();

                if (e instanceof InsufficientCreditException) {
                    response.put("creditAvailable", ((InsufficientCreditException) e).getCreditLimit());
                    response.put("type", "InsufficientCreditError");
                } else {
                    response.put("outOfStockProducts", ((OutOfStockException) e).getOutOfStockProducts());
                    response.put("type", "OutOfStockError");
                }
                JsonResolver.render(response, resp);
            }

        }

    }

}